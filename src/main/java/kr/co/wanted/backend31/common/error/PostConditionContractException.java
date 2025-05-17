package kr.co.wanted.backend31.common.error;

import java.util.Map;
import lombok.ToString;

@ToString(callSuper = true)
public class PostConditionContractException extends InternalErrorException {

  /**
   * @param resp   operation's response
   * @param reason error reason
   */
  public PostConditionContractException(Object resp, String reason) {
    super(Map.of("response", resp, "reason", reason));
  }
}
