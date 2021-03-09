package mybatis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InterceptorChain {

    List<Interceptor> interceptors = new ArrayList<>();

    public <T> T pluginAll(T target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    public InterceptorChain addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
        return this;
    }

    public List<Interceptor> getInterceptors() {
        return Collections.unmodifiableList(interceptors);
    }
}
