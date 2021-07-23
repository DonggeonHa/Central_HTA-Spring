package com.sample.exception;

public class SampleException extends RuntimeException {

	private static final long serialVersionUID = -6710773166728299966L;

	// 미리 정해진 에러코드
	private String code;
	// 에러 제목
	private String title;


	public SampleException(String code, String title, String message) {
        super(message);
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }
}
