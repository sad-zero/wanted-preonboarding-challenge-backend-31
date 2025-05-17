package kr.co.wanted.backend31.common.error;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.ToString;

@ToString(callSuper = true)
public class InvariantContractException extends InternalErrorException {

  /**
   * @param properties {"property": "reason"}
   */
  public InvariantContractException(String classPath, Map<String, String> properties) {
    super(Stream.of(Map.of("classPath", classPath), properties).flatMap(m -> m.entrySet().stream())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
  }

}
