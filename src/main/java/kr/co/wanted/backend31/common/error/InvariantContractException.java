package kr.co.wanted.backend31.common.error;

import java.util.Map;
import lombok.ToString;

@ToString(callSuper = true)
public class InvariantContractException extends ContractException {

  public InvariantContractException(String contractPath, Map<String, Object> details) {
    super(contractPath, details);
  }

  public InvariantContractException(String contractPath, Map<String, Object> details,
      RuntimeException e) {
    super(contractPath, details, e);
  }
}
