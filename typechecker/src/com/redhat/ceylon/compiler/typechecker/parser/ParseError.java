package com.redhat.ceylon.compiler.typechecker.parser;

import org.antlr.runtime.RecognitionException;

import com.redhat.ceylon.compiler.typechecker.tree.Message;

public class ParseError extends RecognitionError {
	
	private CeylonParser parser;
	
	public ParseError(CeylonParser parser, RecognitionException re, String[] tn) {
		super(re, tn);
		this.parser = parser;
	}

	public String getToken() {
		return recognitionException.token.getText();
	}
	
	public String getHeader() {
		return parser.getErrorHeader(recognitionException);
	}
	
	@Override 
	public String getMessage() {
		return parser.getErrorMessage(recognitionException, tokenNames);
	}
	
	@Override
	public String toString() {
		return getMessage();
	}
	
}
