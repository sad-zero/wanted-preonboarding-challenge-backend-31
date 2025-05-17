package kr.co.wanted.backend31.common.error;

import java.util.Collections;
import java.util.Map;
import lombok.ToString;

@ToString(callSuper = true)
public class PreConditionContractException extends InvalidInputException {

  /**
   * @param params {"parameter": "invalid reason"}
   */
  public PreConditionContractException(Map<String, String> params) {
    super(Collections.unmodifiableMap(params));
  }

}
