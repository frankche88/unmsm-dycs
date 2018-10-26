package unmsm.dycs.commons.application.dto;

public class ErrorDto {
	private String message;
	private int errorCode;

	public ErrorDto(int errorCode, String message ) {
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

    public int getErrorCode() {
        return errorCode;
    }



	
}
