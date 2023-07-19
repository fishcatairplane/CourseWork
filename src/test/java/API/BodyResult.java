package API;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BodyResult<T> {
    private String jsonrpc;
    private int id;
    private String error;
    private T result;
}
