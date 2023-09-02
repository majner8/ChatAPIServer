package Security;

public enum SecurityConfigEnum {

	UserIsActiveRole(""),jwtTokenPreflix(""),UserIsActiveClaimName("active"),
	UserIdClaimName("");
	
	 private String value;
	
	 SecurityConfigEnum(String value) {
		this.value=value;
	}
	 public String toString() {
		 return this.value;
	 }
}
