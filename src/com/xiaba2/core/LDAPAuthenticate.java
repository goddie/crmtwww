package com.xiaba2.core;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;


public class LDAPAuthenticate {

	private static LDAPAuthenticate instance;

	public synchronized static LDAPAuthenticate createInstance() {
		if (instance == null) {
			try {
				instance = (LDAPAuthenticate) Class.forName("com.xiaba2.core.LDAPAuthenticate").newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return instance;
	}

	private String LDAP_IP = "ldap.mediamarina.com";
	private String LDAP_USER = "cn=admin,dc=w3pc,dc=com";
	private String LDAP_PASSWORD = "123456";
	private String LDAP_BASE = "ou=Users,dc=w3pc,dc=com";

	private DirContext ds;
	 

    /**
     * 
     * 
		LDAP属性
		定义
		o Organization：组织
		ou Organization unit：组织单元
		uid Userid：用户id
		cn  Common name：常见名称
		sn 姓 givenname 首名 
		dn Distinguished Name：区分名称
     * @param username
     * @throws NamingException
     */
    public void search(String username) throws NamingException {
        System.out.println("Searching...");
        SearchControls searchCtls = new SearchControls();
 
        // Specify the search scope
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        // specify the LDAP search filter
        String searchFilter = "uid="+username;
 
        // Specify the Base for the search
        String searchBase = LDAP_BASE;
 
        // Specify the attributes to return
        String returnedAtts[] = { "cn" };
        searchCtls.setReturningAttributes(returnedAtts);
 
        // Search for objects using the filter
        NamingEnumeration<SearchResult> entries = ds.search(searchBase,
                searchFilter, searchCtls);
 
        // Loop through the search results
        while (entries.hasMoreElements()) {
            SearchResult entry = entries.next();
            System.out.println(">>>" + entry.getName());
            // Print out the groups
            Attributes attrs = entry.getAttributes();
            if (attrs != null) {
                for (NamingEnumeration<? extends Attribute> names = attrs
                        .getAll(); names.hasMore();) {
                    Attribute attr = names.next();
                    System.out.println("AttributeID: " + attr.getID());
                    	
                    for (NamingEnumeration<?> e = attr.getAll(); e.hasMore();) {
                        System.out.println("Attributes:" + e.next());
                    }
                }
            }
        }
        System.out.println("Search complete.");
    }

    public void update(String username,String password) {
    	
    	try {
			connect();
			
	        System.out.println("Updating...");
	         ModificationItem[] mods = new ModificationItem[1];
	         Attribute attr = new BasicAttribute("userPassword", password);
	          
	         // Support add, replace and remove an attribute.
	         mods[0] = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, attr);
	         ds.modifyAttributes("uid="+username+","+LDAP_BASE, mods);
	        System.out.println("Updated.");
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				close();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	

    }
 

    public boolean add(String username,String password) {
    	
    	
    	
        System.out.println("Adding...");
        
		// MessageDigest md;
		//
		// md = MessageDigest.getInstance("MD5");
		// md.update(password.getBytes());
		//
		// byte[] bs = md.digest();
		//byte[] base64 = Base64.encode(bs);
		//System.out.println("base64->"+new String(base64));
       
        try {
        	connect();
        	
        	Attribute cn = new BasicAttribute("cn", username);  
	        Attribute sn = new BasicAttribute("sn", username);  
	        //Attribute mail = new BasicAttribute("mail", username); 
	        Attribute pwd = new BasicAttribute("userPassword", password); 
	        Attribute oc = new BasicAttribute("objectClass"); 
	        oc.add("inetOrgPerson");
	        oc.add("uidObject");
	        oc.add("top");
	        
	        Attributes entry = new BasicAttributes();
	        entry.put(cn);  
	        entry.put(sn);
	        //entry.put(mail);
	        entry.put(pwd);
	        entry.put(oc);  
	 
	        this.ds.createSubcontext("uid="+username+","+LDAP_BASE, entry);
	        
	        System.out.println("Add complete.");
	        
	        
	        return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}finally {
			try {
				close();
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
        
        
    }
    
    
    public boolean checkUser(String username,String password){
 
        Hashtable<String, Object> env = new Hashtable<String, Object>(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://"+LDAP_IP+":389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "uid="+username+",ou=Users,dc=w3pc,dc=com");
        env.put(Context.SECURITY_CREDENTIALS, password);
        
        
        try {
			new InitialDirContext(env);
			System.out.println("checkUser true:"+username);
			return true;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		}

 
    }
 

    public void delete() throws NamingException {
        System.out.println("Deleting...");
        this.ds.destroySubcontext("uid=test,ou=tester,dc=ibm,dc=com");
        System.out.println("Deleted.");
    }
 
    
    
    

    public synchronized void connect() throws NamingException {
        System.out.println("connecting...");
        if (ds == null) {
            Hashtable<String, Object> env = new Hashtable<String, Object>(11);
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://"+LDAP_IP+":389");
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put(Context.SECURITY_PRINCIPAL, LDAP_USER);
            env.put(Context.SECURITY_CREDENTIALS, LDAP_PASSWORD);
 
            ds = new InitialDirContext(env);
            // ds = (DirContext) initial.lookup("ldap://localhost:389");
        }
        
        if(ds!=null)
        {
        	System.out.println("connected.");
        }
        else
        {
        	System.out.println("connect Fail !");
        }
    }
 

    public void close() throws NamingException {
        System.out.println("closing...");
        ds.close();
        System.out.println("closed.");
    }
}
