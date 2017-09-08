package com.app.ldap;

import java.util.Hashtable;

import javax.naming.Context;

import javax.naming.NamingException;

import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;


import com.app.constants.LDAPConstants;

public class LdapAuthentication {

 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String ldapLoginAuth(String username,String pwd) {
		Hashtable env = new Hashtable();
		boolean b = false;
		String result;


		env.put(Context.INITIAL_CONTEXT_FACTORY,LDAPConstants.CONTEXTFACTORY);
		env.put(Context.PROVIDER_URL, LDAPConstants.PROVIDERURL);
		env.put(Context.SECURITY_AUTHENTICATION, LDAPConstants.SECURITYAUTHENTICATION);
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, pwd);
		DirContext ctx = null;
		try {
			ctx = new InitialDirContext(env);
			b = true;
			ctx.close();
		} catch (NamingException e) {
			b = false;
		}finally{
			if(b){
				result = "SUCCESS";
			}else{
				result = "FAILURE";
			}
		}
		return result;
	}
}
