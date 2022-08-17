package postsite.postsitespring.common.Api;

public class ApiUtil {
    public static <T>ApiResult<T> success(T response) {
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> error(String message, int status){
        return new ApiResult<>(false, null, new ApiError(message, status));
    }
}
