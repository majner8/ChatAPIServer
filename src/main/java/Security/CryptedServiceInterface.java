package Security;

public interface CryptedServiceInterface {

	public String CryptId(long Id);
	/**@throws BasicTextEncryptor if crypted message is not long */
	public long DecryptId(String character);
}
