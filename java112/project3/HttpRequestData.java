/**
 * HttpRequestData holds property data
 * for requests that will be sent to
 * a server.
 *
 * @author Chelsea Greger
 * @since v1.0
 */

package java112.project3;

import java.util.*;

public class HttpRequestData {

    // Instance variables
    private int remoteComputer;
    private String remoteComputerAddress;
    private String httpMethodOfRequest;
    private String requestURI;
    private StringBuffer requestURL;
    private String requestProtocol;
    private String serverName;
    private int serverPortNumber;
    private Locale serverLocale;
    private String queryString;
    private String queryParameter;
    private String userAgent;

    public HttpRequestData() { }

	/**
	* Returns value of remoteComputer
	* @return
	*/
	public int getRemoteComputer() {
		return remoteComputer;
	}

	/**
	* Returns value of remoteComputerAddress
	* @return
	*/
	public String getRemoteComputerAddress() {
		return remoteComputerAddress;
	}

	/**
	* Returns value of httpMethodOfRequest
	* @return
	*/
	public String getHttpMethodOfRequest() {
		return httpMethodOfRequest;
	}

	/**
	* Returns value of requestURI
	* @return
	*/
	public String getRequestURI() {
		return requestURI;
	}

	/**
	* Returns value of requestURL
	* @return
	*/
	public StringBuffer getRequestURL() {
		return requestURL;
	}

	/**
	* Returns value of requestProtocol
	* @return
	*/
	public String getRequestProtocol() {
		return requestProtocol;
	}

	/**
	* Returns value of serverName
	* @return
	*/
	public String getServerName() {
		return serverName;
	}

	/**
	* Returns value of serverPortNumber
	* @return
	*/
	public int getServerPortNumber() {
		return serverPortNumber;
	}

	/**
	* Returns value of serverLocale
	* @return
	*/
	public Locale getServerLocale() {
		return serverLocale;
	}

	/**
	* Returns value of queryString
	* @return
	*/
	public String getQueryString() {
		return queryString;
	}

	/**
	* Returns value of queryParameter
	* @return
	*/
	public String getQueryParameter() {
		return queryParameter;
	}

	/**
	* Returns value of requestHeader
	* @return
	*/
	public String getUserAgent() {
		return userAgent;
	}

	/**
	* Sets new value of remoteComputer
	* @param
	*/
	public void setRemoteComputer(int remoteComputer) {
		this.remoteComputer = remoteComputer;
	}

	/**
	* Sets new value of remoteComputerAddress
	* @param
	*/
	public void setRemoteComputerAddress(String remoteComputerAddress) {
		this.remoteComputerAddress = remoteComputerAddress;
	}

	/**
	* Sets new value of httpMethodOfRequest
	* @param
	*/
	public void setHttpMethodOfRequest(String httpMethodOfRequest) {
		this.httpMethodOfRequest = httpMethodOfRequest;
	}

	/**
	* Sets new value of requestURI
	* @param
	*/
	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	/**
	* Sets new value of requestURL
	* @param
	*/
	public void setRequestURL(StringBuffer requestURL) {
		this.requestURL = requestURL;
	}

	/**
	* Sets new value of requestProtocol
	* @param
	*/
	public void setRequestProtocol(String requestProtocol) {
		this.requestProtocol = requestProtocol;
	}

	/**
	* Sets new value of serverName
	* @param
	*/
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	* Sets new value of serverPortNumber
	* @param
	*/
	public void setServerPortNumber(int serverPortNumber) {
		this.serverPortNumber = serverPortNumber;
	}

	/**
	* Sets new value of serverLocale
	* @param
	*/
	public void setServerLocale(Locale serverLocale) {
		this.serverLocale = serverLocale;
	}

	/**
	* Sets new value of queryString
	* @param
	*/
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	/**
	* Sets new value of queryParameter
	* @param
	*/
	public void setQueryParameter(String queryParameter) {
		this.queryParameter = queryParameter;
	}

	/**
	* Sets new value of requestHeader
	* @param
	*/
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
}
