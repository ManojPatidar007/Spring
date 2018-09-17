package netcracker.developer.imp;

public class ExceptionHandling extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private String exceptionMsg;

	
	public ExceptionHandling(String exceptionMsg){
		this.exceptionMsg=exceptionMsg;
	}
	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	
	

}