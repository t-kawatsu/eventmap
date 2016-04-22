package in.eventmap.common.interceptor;

public interface LoginAuthorizationAware<T> {

	public boolean getIsLogined();

	public T getCurrentUser();

	public void removeCurrentUser();

	public void setCurrentUser(T user);
}
