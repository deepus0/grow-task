package au.com.deep.grow.model.xero;

import lombok.Data;

@Data
public class IdentityTokenRequest {

    private String grant_type;
    private String code;
    private String redirect_uri;
}
