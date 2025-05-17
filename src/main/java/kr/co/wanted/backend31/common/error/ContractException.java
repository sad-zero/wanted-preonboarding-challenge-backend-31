package kr.co.wanted.backend31.common.error;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class ContractException extends InternalErrorException {

  public ContractException(String contractPath, Map<String, Object> details) {
    super(
        Stream.of(Map.of("contractPath", contractPath), details).flatMap(m -> m.entrySet().stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
  }

  public ContractException(String contractPath, Map<String, Object> details, RuntimeException e) {
    super(
        Stream.of(Map.of("contractPath", contractPath), details).flatMap(m -> m.entrySet().stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), e);
  }
}
