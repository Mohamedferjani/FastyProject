/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fasty.gui;

/**
 *
 * @author FERJANI
 */
import com.restfb.*;
import com.restfb.types.*;
import com.restfb.exception.*;

public class FacebookGroupPost {
    public static void main(String[] args) {
        String accessToken = "EAADELBJHovIBALLxmZCBvQoH1AV7pXe9sbdVHyZAZCGKMvWCTNKALb75QnRZB3JfQZCQZArJfBh8GzeM64Fhu4A0E47ZBUkgB0S8iNdMCVoWj1PMZBAOaLO4m6RKnRoVxY7ZATAsGQOUQLmTkceyEpZCV3h2YBZB5kDWGQiHKVZBIxRMdqS0hIBueS7ZCZBZA7vNTCHE8hjg5pqR6n2dc2jo8PGShyU";
        String pageId = "102900889410694"; // replace with your page ID
        String message = "Test!";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);

        try {
            FacebookType result = fbClient.publish(pageId + "/feed", FacebookType.class,
                    Parameter.with("message", message));
            System.out.println("Post published on page: " + result.getId());
        } catch (FacebookOAuthException ex) {
            System.err.println("Failed to post on page: " + ex.getMessage());
        }
    }
    
}

