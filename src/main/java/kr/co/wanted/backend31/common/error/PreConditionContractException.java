package kr.co.wanted.backend31.common.error;

import java.util.Map;
import lombok.ToString;

@ToString(callSuper = true)
public class PreConditionContractException extends ContractException {


  public PreConditionContractException(String contractPath, Map<String, Object> details) {
    super(contractPath, details);
  }

  public PreConditionContractException(String contractPath, Map<String, Object> details,
      RuntimeException e) {
    super(contractPath, details, e);
  }
}
