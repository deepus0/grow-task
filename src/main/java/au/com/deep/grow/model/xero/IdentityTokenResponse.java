package au.com.deep.grow.model.xero;

import lombok.Data;

@Data
public class IdentityTokenResponse {

    private String access_token;
    private String id_token;
    private Integer expires_in;
    private String token_type;
    private String refresh_token;
}
