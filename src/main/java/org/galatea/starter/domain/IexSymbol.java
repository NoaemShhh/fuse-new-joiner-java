package org.galatea.starter.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IexSymbol {

  private String symbol;
  private String name;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date date;
  private boolean isEnabled;
  private String type;
  private String iexId;

}
