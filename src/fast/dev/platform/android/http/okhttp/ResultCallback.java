package fast.dev.platform.android.http.okhttp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;

public abstract class ResultCallback<T> {

	public Type mType;

	public ResultCallback() {
		mType = getSuperclassTypeParameter(getClass());
	}

	static Type getSuperclassTypeParameter(Class<?> subclass) {
		Type superclass = subclass.getGenericSuperclass();
		if (superclass instanceof Class) {
			throw new RuntimeException("Missing type parameter.");
		}
		ParameterizedType parameterized = (ParameterizedType) superclass;
		return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
	}

	public void onBefore(Request request) {

	}

	public void onAfter() {

	}

	public void inProgress(float progress) {

	}

	public abstract String onError(Request request, Exception e);

	public abstract String onResponse(T response);

	public static final ResultCallback<String> DEFAULT_RESULT_CALLBACK = new ResultCallback<String>() {

		@Override
		public String onError(Request request, Exception e) {
			return e.getMessage();
		}

		@Override
		public String onResponse(String response) {
			return response;
		}

	};

}
