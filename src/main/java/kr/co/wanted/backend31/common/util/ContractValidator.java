package kr.co.wanted.backend31.common.util;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import kr.co.wanted.backend31.common.error.InvariantContractException;
import kr.co.wanted.backend31.common.error.PostConditionContractException;
import kr.co.wanted.backend31.common.error.PreConditionContractException;
import lombok.extern.slf4j.Slf4j;

/**
 * Validator for Design by Contract
 *
 * @param <T> target message or object
 */
@Slf4j
public final class ContractValidator<T> {

  private final String contractPath;
  private final ContractType contractType;

  /**
   * Initialize the validator
   *
   * @param contractPath class or operation's path
   * @param contractType contract type
   * @throws InvariantContractException invalid arguments
   */
  private ContractValidator(@NotBlank String contractPath, @NotNull ContractType contractType) {
    final var properties = new HashMap<String, Object>();

    if ((contractPath == null) || contractPath.isBlank()) {
      properties.put("contractPath", "null");
    }
    if (contractType == null) {
      properties.put("contractType", "null");
    }
    if (!properties.isEmpty()) {
      throw new InvariantContractException(ContractValidator.class.getName(), properties);
    }

    this.contractPath = contractPath;
    this.contractType = contractType;
  }

  /**
   * Create operation's precondition contract validator. <br/> To apply this, operations should be
   * currying.
   *
   * @param operationPath operation's path like <code>com.happy.Power.love</code>
   * @param <T>           message's type
   * @return Initialized validator
   * @throws InvariantContractException invalid arguments
   */
  public static <T> ContractValidator<T> preCondition(@NotBlank String operationPath) {
    return new ContractValidator<>(operationPath, ContractType.PRECONDITION);
  }

  /**
   * Create operation's postcondition contract validator.
   *
   * @param operationPath operation's path like <code>com.happy.Power.love</code>
   * @param <T>           message's type
   * @return Initialized validator
   * @throws InvariantContractException invalid arguments
   */
  public static <T> ContractValidator<T> postCondition(@NotBlank String operationPath) {
    return new ContractValidator<>(operationPath, ContractType.POSTCONDITION);
  }

  /**
   * Create operation's invariant contract validator.
   *
   * @param classPath class's path like <code>com.happy.Power</code>
   * @param <T>       message's type
   * @return Initialized validator
   * @throws InvariantContractException invalid arguments
   */
  public static <T> ContractValidator<T> invariantCondition(@NotBlank String classPath) {
    return new ContractValidator<>(classPath, ContractType.INVARIANT);
  }

  /**
   * Test this contract to the target
   *
   * @param target message or class
   * @throws PreConditionContractException  whether <code>check</code> fails itself or
   *                                        <code>ContractType.PRECONDITION</code> fails.
   * @throws PostConditionContractException <code>ContractType.POSTCONDITION</code> fails.
   * @throws InvariantContractException     <code>ContractType.INVARIANT</code> fails.
   */
  public void check(@NotNull T target) {
    if (target == null) {
      throw new PreConditionContractException(ContractValidator.class.getName(),
          Map.of("target", "null"));
    }
    try (final var validatorFactory = buildDefaultValidatorFactory()) {
      final var validator = validatorFactory.getValidator();
      final var errors = validator.validate(target);
      if (errors.isEmpty()) {
        return;
      }
      final Map<String, Object> properties = errors.stream().collect(
          Collectors.toMap(violation -> violation.getPropertyPath().toString(),
              ConstraintViolation::getMessage));
      switch (contractType) {
        case PRECONDITION -> throw new PreConditionContractException(this.contractPath, properties);
        case POSTCONDITION ->
            throw new PostConditionContractException(this.contractPath, properties);
        case INVARIANT -> throw new InvariantContractException(this.contractPath, properties);
      }
    } catch (ValidationException e) {
      throw new PreConditionContractException(ContractValidator.class.getName(), Map.of(), e);
    }
  }

  private enum ContractType {
    INVARIANT, PRECONDITION, POSTCONDITION,
  }
}