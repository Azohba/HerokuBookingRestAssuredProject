package models.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetTokenRequest {
    String username;
    String password;

}
