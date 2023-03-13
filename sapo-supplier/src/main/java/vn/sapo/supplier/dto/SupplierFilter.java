package vn.sapo.supplier.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minidev.json.parser.JSONParser;
import vn.sapo.entities.supplier.SupplierStatus;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class SupplierFilter {
    private String filter;
    private List<SupplierStatus> statuses;
    private List<Integer> employeeIds;
    private List<Integer> groupIds;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd", timezone = "UCT")
    private Date createdFrom;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="yyyy-MM-dd", timezone = "UCT")
    private Date createdTo;
    private List<String> tags;
    private Integer pageNo;
    private Integer pageSize;


}
