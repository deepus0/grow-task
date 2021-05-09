package au.com.deep.grow.model.xero;

import lombok.Data;

@Data
public class XeroTenant {

    private String id;
    private String authEventId;
    private String tenantId;
    private String tenantType;
    private String tenantName;
    private String createdDateUtc;
    private String updatedDateUtc;
}
