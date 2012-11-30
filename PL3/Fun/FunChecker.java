// $ANTLR 3.4 FunChecker.g 2012-11-30 02:49:23

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class FunChecker extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASSN", "BOOL", "COLON", "COMMENT", "DIGIT", "DIV", "DOT", "ELSE", "EOL", "EQ", "FALSE", "FOR", "FORMAL", "FUNC", "FUNCCALL", "GT", "ID", "IF", "IFELSE", "INT", "LETTER", "LPAR", "LT", "MINUS", "NOACTUAL", "NOFORMAL", "NOT", "NUM", "PLUS", "PROC", "PROCCALL", "PROG", "RETURN", "RPAR", "SEQ", "SPACE", "TIMES", "TO", "TRUE", "VAR", "WHILE"
    };

    public static final int EOF=-1;
    public static final int ASSN=4;
    public static final int BOOL=5;
    public static final int COLON=6;
    public static final int COMMENT=7;
    public static final int DIGIT=8;
    public static final int DIV=9;
    public static final int DOT=10;
    public static final int ELSE=11;
    public static final int EOL=12;
    public static final int EQ=13;
    public static final int FALSE=14;
    public static final int FOR=15;
    public static final int FORMAL=16;
    public static final int FUNC=17;
    public static final int FUNCCALL=18;
    public static final int GT=19;
    public static final int ID=20;
    public static final int IF=21;
    public static final int IFELSE=22;
    public static final int INT=23;
    public static final int LETTER=24;
    public static final int LPAR=25;
    public static final int LT=26;
    public static final int MINUS=27;
    public static final int NOACTUAL=28;
    public static final int NOFORMAL=29;
    public static final int NOT=30;
    public static final int NUM=31;
    public static final int PLUS=32;
    public static final int PROC=33;
    public static final int PROCCALL=34;
    public static final int PROG=35;
    public static final int RETURN=36;
    public static final int RPAR=37;
    public static final int SEQ=38;
    public static final int SPACE=39;
    public static final int TIMES=40;
    public static final int TO=41;
    public static final int TRUE=42;
    public static final int VAR=43;
    public static final int WHILE=44;

    // delegates
    public TreeParser[] getDelegates() {
        return new TreeParser[] {};
    }

    // delegators


    public FunChecker(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public FunChecker(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return FunChecker.tokenNames; }
    public String getGrammarFileName() { return "FunChecker.g"; }



    	// Contextual errors

    	private int errorCount = 0;

    	private void reportError (String message,
    	                          CommonTree ast) {
    	// Print an error message relating to the given 
    	// (sub)AST.
    		int line = ast.getLine(),
    		    column = ast.getCharPositionInLine() ;
    		System.err.println("line " + line + ":" + column
    		   + " " + message);
    		errorCount++;
    	}

    	public int getNumberOfContextualErrors () {
    	// Return the total number of errors so far detected.
    		return errorCount;
    	}


    	// Scope checking

    	private SymbolTable<Type> typeTable =
    	   new SymbolTable<Type>();

    	private void predefine () {
    	// Add predefined procedures to the type table.
    		typeTable.put("read",
    		   new Type.Mapping(Type.VOID, Type.INT));
    		typeTable.put("write",
    		   new Type.Mapping(Type.INT, Type.VOID));
    	}

    	private void define (String id, Type type,
    	                     CommonTree decl) {
    	// Add id with its type to the type table, checking 
    	// that id is not already declared in the same scope.
    		boolean ok = typeTable.put(id, type);
    		if (!ok)
    			reportError(id + " is redeclared", decl);
    	}

    	private Type retrieve (String id, CommonTree occ) {
    	// Retrieve id's type from the type table.
    		Type type = typeTable.get(id);
    		if (type == null) {
    			reportError(id + " is undeclared", occ);
    			return Type.ERROR;
    		} else
    			return type;
    	}


    	// Type checking

    	private static final Type.Mapping
    	   NOTTYPE = new Type.Mapping(Type.BOOL, Type.BOOL),
    	   COMPTYPE = new Type.Mapping(
    	      new Type.Pair(Type.INT, Type.INT), Type.BOOL),
    	   ARITHTYPE = new Type.Mapping(
    	      new Type.Pair(Type.INT, Type.INT), Type.INT),
    	   MAINTYPE = new Type.Mapping(Type.VOID, Type.VOID);

    	private void checkType (Type typeExpected,
    	                        Type typeActual,
    	                        CommonTree construct) {
    	// Check that a construct's actual type matches 
    	// the expected type.
    		if (! typeActual.equiv(typeExpected))
    			reportError("type is " + typeActual
    			   + ", should be " + typeExpected,
    			   construct);
    	}

    	private Type checkCall (String id, Type typeArg,
    	                        CommonTree call) {
    	// Check that a procedure call identifies a procedure 
    	// and that its argument type matches the proecure's 
    	// type. Return the type of the procedure call.
    		Type typeProc = retrieve(id, call);
    		if (! (typeProc instanceof Type.Mapping)) {
    			reportError(id + " is not a procedure", call);
    			return Type.ERROR;
    		} else {
    			Type.Mapping mapping = (Type.Mapping)typeProc;
    			checkType(mapping.domain, typeArg, call);
    			return mapping.range;
    		}
    	}

    	private Type checkUnary (Type.Mapping typeOp,
    	                         Type typeArg,
    	                         CommonTree op) {
    	// Check that a unary operator's operand type matches 
    	// the operator's type. Return the type of the operator 
    	// application.
    		if (! (typeOp.domain instanceof Type.Primitive))
    			reportError(
    			   "unary operator should have 1 operand",
    			   op);
    		else
    			checkType(typeOp.domain, typeArg, op);
    		return typeOp.range;
    	}

    	private Type checkBinary (Type.Mapping typeOp,
    	                          Type typeArg1, Type typeArg2,
    	                          CommonTree op) {
    	// Check that a binary operator's operand types match 
    	// the operator's type. Return the type of the operator 
    	// application.
    		if (! (typeOp.domain instanceof Type.Pair))
    			reportError(
    			   "binary operator should have 2 operands",
    			   op);
    		else {
    			Type.Pair pair =
    			   (Type.Pair)typeOp.domain;
    			checkType(pair.first, typeArg1, op);
    			checkType(pair.second, typeArg2, op);
    		}
    		return typeOp.range;
    	}




    // $ANTLR start "program"
    // FunChecker.g:156:1: program : ^( PROG ( var_decl )* ( proc_decl )+ ) ;
    public final void program() throws RecognitionException {
        CommonTree PROG1=null;

        try {
            // FunChecker.g:157:2: ( ^( PROG ( var_decl )* ( proc_decl )+ ) )
            // FunChecker.g:157:4: ^( PROG ( var_decl )* ( proc_decl )+ )
            {
            PROG1=(CommonTree)match(input,PROG,FOLLOW_PROG_in_program60); 

             predefine(); 

            match(input, Token.DOWN, null); 
            // FunChecker.g:159:5: ( var_decl )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==VAR) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // FunChecker.g:159:5: var_decl
            	    {
            	    pushFollow(FOLLOW_var_decl_in_program72);
            	    var_decl();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            // FunChecker.g:160:5: ( proc_decl )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==FUNC||LA2_0==PROC) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // FunChecker.g:160:5: proc_decl
            	    {
            	    pushFollow(FOLLOW_proc_decl_in_program79);
            	    proc_decl();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            match(input, Token.UP, null); 


             Type tmain = retrieve("main", PROG1);
            				  checkType(tmain, MAINTYPE, PROG1);
            				

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "program"



    // $ANTLR start "proc_decl"
    // FunChecker.g:169:1: proc_decl : ( ^( PROC ID t= formal ( var_decl )* com ) | ^( FUNC t1= type ID t2= formal ( var_decl )* com t3= expr ) );
    public final void proc_decl() throws RecognitionException {
        CommonTree ID2=null;
        CommonTree PROC3=null;
        CommonTree ID4=null;
        CommonTree FUNC5=null;
        Type t =null;

        Type t1 =null;

        Type t2 =null;

        Type t3 =null;


        try {
            // FunChecker.g:170:2: ( ^( PROC ID t= formal ( var_decl )* com ) | ^( FUNC t1= type ID t2= formal ( var_decl )* com t3= expr ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==PROC) ) {
                alt5=1;
            }
            else if ( (LA5_0==FUNC) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }
            switch (alt5) {
                case 1 :
                    // FunChecker.g:170:4: ^( PROC ID t= formal ( var_decl )* com )
                    {
                    PROC3=(CommonTree)match(input,PROC,FOLLOW_PROC_in_proc_decl102); 

                    match(input, Token.DOWN, null); 
                    ID2=(CommonTree)match(input,ID,FOLLOW_ID_in_proc_decl108); 

                     typeTable.enterLocalScope();
                    				

                    pushFollow(FOLLOW_formal_in_proc_decl122);
                    t=formal();

                    state._fsp--;


                     Type proctype =
                    				    new Type.Mapping(t, Type.VOID);
                    				  define((ID2!=null?ID2.getText():null), proctype, PROC3);
                    				  // ... to enable recursion
                    				

                    // FunChecker.g:180:5: ( var_decl )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==VAR) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // FunChecker.g:180:5: var_decl
                    	    {
                    	    pushFollow(FOLLOW_var_decl_in_proc_decl134);
                    	    var_decl();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    pushFollow(FOLLOW_com_in_proc_decl141);
                    com();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     typeTable.exitLocalScope();
                    				  // Enter the function in the local 
                    				  // scope, to enable it to be recursive:
                    				  define((ID2!=null?ID2.getText():null), proctype, PROC3);
                    				

                    }
                    break;
                case 2 :
                    // FunChecker.g:187:4: ^( FUNC t1= type ID t2= formal ( var_decl )* com t3= expr )
                    {
                    FUNC5=(CommonTree)match(input,FUNC,FOLLOW_FUNC_in_proc_decl154); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_in_proc_decl162);
                    t1=type();

                    state._fsp--;


                    ID4=(CommonTree)match(input,ID,FOLLOW_ID_in_proc_decl168); 

                     typeTable.enterLocalScope();
                    				

                    pushFollow(FOLLOW_formal_in_proc_decl182);
                    t2=formal();

                    state._fsp--;


                     Type functype =
                    				    new Type.Mapping(t2, t1);
                    				  define((ID4!=null?ID4.getText():null), functype, FUNC5);
                    				  // ... to enable recursion
                    				

                    // FunChecker.g:198:5: ( var_decl )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==VAR) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // FunChecker.g:198:5: var_decl
                    	    {
                    	    pushFollow(FOLLOW_var_decl_in_proc_decl194);
                    	    var_decl();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    pushFollow(FOLLOW_com_in_proc_decl201);
                    com();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_proc_decl209);
                    t3=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     typeTable.exitLocalScope();
                    				  define((ID4!=null?ID4.getText():null), functype, FUNC5);
                    				  checkType(t1, t3, FUNC5);
                    				

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "proc_decl"



    // $ANTLR start "formal"
    // FunChecker.g:207:1: formal returns [Type type] : ( ^( FORMAL t= type ID ) | NOFORMAL );
    public final Type formal() throws RecognitionException {
        Type type = null;


        CommonTree ID6=null;
        CommonTree FORMAL7=null;
        Type t =null;


        try {
            // FunChecker.g:208:2: ( ^( FORMAL t= type ID ) | NOFORMAL )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==FORMAL) ) {
                alt6=1;
            }
            else if ( (LA6_0==NOFORMAL) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // FunChecker.g:208:4: ^( FORMAL t= type ID )
                    {
                    FORMAL7=(CommonTree)match(input,FORMAL,FOLLOW_FORMAL_in_formal234); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_in_formal238);
                    t=type();

                    state._fsp--;


                    ID6=(CommonTree)match(input,ID,FOLLOW_ID_in_formal240); 

                    match(input, Token.UP, null); 


                     define((ID6!=null?ID6.getText():null), t, FORMAL7);
                    				  type = t;
                    				

                    }
                    break;
                case 2 :
                    // FunChecker.g:212:4: NOFORMAL
                    {
                    match(input,NOFORMAL,FOLLOW_NOFORMAL_in_formal252); 

                     type = Type.VOID; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return type;
    }
    // $ANTLR end "formal"



    // $ANTLR start "var_decl"
    // FunChecker.g:216:1: var_decl : ^( VAR t1= type ID t2= expr ) ;
    public final void var_decl() throws RecognitionException {
        CommonTree ID8=null;
        CommonTree VAR9=null;
        Type t1 =null;

        Type t2 =null;


        try {
            // FunChecker.g:217:2: ( ^( VAR t1= type ID t2= expr ) )
            // FunChecker.g:217:4: ^( VAR t1= type ID t2= expr )
            {
            VAR9=(CommonTree)match(input,VAR,FOLLOW_VAR_in_var_decl270); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_var_decl274);
            t1=type();

            state._fsp--;


            ID8=(CommonTree)match(input,ID,FOLLOW_ID_in_var_decl276); 

            pushFollow(FOLLOW_expr_in_var_decl280);
            t2=expr();

            state._fsp--;


            match(input, Token.UP, null); 


             define((ID8!=null?ID8.getText():null), t1, VAR9);
            				  checkType(t1, t2, VAR9);
            				

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "var_decl"



    // $ANTLR start "type"
    // FunChecker.g:223:1: type returns [Type type] : ( BOOL | INT );
    public final Type type() throws RecognitionException {
        Type type = null;


        try {
            // FunChecker.g:224:2: ( BOOL | INT )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==BOOL) ) {
                alt7=1;
            }
            else if ( (LA7_0==INT) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;

            }
            switch (alt7) {
                case 1 :
                    // FunChecker.g:224:4: BOOL
                    {
                    match(input,BOOL,FOLLOW_BOOL_in_type305); 

                     type = Type.BOOL; 

                    }
                    break;
                case 2 :
                    // FunChecker.g:225:4: INT
                    {
                    match(input,INT,FOLLOW_INT_in_type313); 

                     type = Type.INT; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return type;
    }
    // $ANTLR end "type"



    // $ANTLR start "com"
    // FunChecker.g:231:1: com : ( ^( ASSN ID t= expr ) | ^( PROCCALL ID t= expr ) | ^( IF t= expr com ) | ^( IFELSE t= expr com com ) | ^( WHILE t= expr com ) | ^( FOR ID ASSN t= expr TO s= expr com ) | ^( SEQ ( com )* ) );
    public final void com() throws RecognitionException {
        CommonTree ID10=null;
        CommonTree ASSN11=null;
        CommonTree ID12=null;
        CommonTree PROCCALL13=null;
        CommonTree IF14=null;
        CommonTree IFELSE15=null;
        CommonTree WHILE16=null;
        CommonTree FOR17=null;
        Type t =null;

        Type s =null;


        try {
            // FunChecker.g:232:2: ( ^( ASSN ID t= expr ) | ^( PROCCALL ID t= expr ) | ^( IF t= expr com ) | ^( IFELSE t= expr com com ) | ^( WHILE t= expr com ) | ^( FOR ID ASSN t= expr TO s= expr com ) | ^( SEQ ( com )* ) )
            int alt9=7;
            switch ( input.LA(1) ) {
            case ASSN:
                {
                alt9=1;
                }
                break;
            case PROCCALL:
                {
                alt9=2;
                }
                break;
            case IF:
                {
                alt9=3;
                }
                break;
            case IFELSE:
                {
                alt9=4;
                }
                break;
            case WHILE:
                {
                alt9=5;
                }
                break;
            case FOR:
                {
                alt9=6;
                }
                break;
            case SEQ:
                {
                alt9=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }

            switch (alt9) {
                case 1 :
                    // FunChecker.g:232:4: ^( ASSN ID t= expr )
                    {
                    ASSN11=(CommonTree)match(input,ASSN,FOLLOW_ASSN_in_com331); 

                    match(input, Token.DOWN, null); 
                    ID10=(CommonTree)match(input,ID,FOLLOW_ID_in_com333); 

                    pushFollow(FOLLOW_expr_in_com337);
                    t=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     Type tvar =
                    				    retrieve((ID10!=null?ID10.getText():null), ASSN11);
                    				  checkType(tvar, t, ASSN11);
                    				

                    }
                    break;
                case 2 :
                    // FunChecker.g:237:4: ^( PROCCALL ID t= expr )
                    {
                    PROCCALL13=(CommonTree)match(input,PROCCALL,FOLLOW_PROCCALL_in_com350); 

                    match(input, Token.DOWN, null); 
                    ID12=(CommonTree)match(input,ID,FOLLOW_ID_in_com352); 

                    pushFollow(FOLLOW_expr_in_com356);
                    t=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     Type tres = checkCall(
                    				    (ID12!=null?ID12.getText():null), t, PROCCALL13);
                    				  if (! tres.equiv(Type.VOID))
                    				    reportError(
                    				      "procedure should be void",
                    				      PROCCALL13);
                    				

                    }
                    break;
                case 3 :
                    // FunChecker.g:245:4: ^( IF t= expr com )
                    {
                    IF14=(CommonTree)match(input,IF,FOLLOW_IF_in_com369); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_com373);
                    t=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_com_in_com375);
                    com();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     checkType(Type.BOOL, t, IF14);
                    				

                    }
                    break;
                case 4 :
                    // FunChecker.g:248:4: ^( IFELSE t= expr com com )
                    {
                    IFELSE15=(CommonTree)match(input,IFELSE,FOLLOW_IFELSE_in_com388); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_com392);
                    t=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_com_in_com394);
                    com();

                    state._fsp--;


                    pushFollow(FOLLOW_com_in_com396);
                    com();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     checkType(Type.BOOL, t, IFELSE15);
                    				

                    }
                    break;
                case 5 :
                    // FunChecker.g:251:4: ^( WHILE t= expr com )
                    {
                    WHILE16=(CommonTree)match(input,WHILE,FOLLOW_WHILE_in_com409); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_com413);
                    t=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_com_in_com415);
                    com();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     checkType(Type.BOOL, t, WHILE16);
                    				

                    }
                    break;
                case 6 :
                    // FunChecker.g:254:4: ^( FOR ID ASSN t= expr TO s= expr com )
                    {
                    FOR17=(CommonTree)match(input,FOR,FOLLOW_FOR_in_com428); 

                    match(input, Token.DOWN, null); 
                    match(input,ID,FOLLOW_ID_in_com430); 

                    match(input,ASSN,FOLLOW_ASSN_in_com432); 

                    pushFollow(FOLLOW_expr_in_com436);
                    t=expr();

                    state._fsp--;


                    match(input,TO,FOLLOW_TO_in_com438); 

                    pushFollow(FOLLOW_expr_in_com442);
                    s=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_com_in_com444);
                    com();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     	checkType(Type.INT, t, FOR17);
                    					checkType(Type.INT, s, FOR17);
                    				

                    }
                    break;
                case 7 :
                    // FunChecker.g:258:4: ^( SEQ ( com )* )
                    {
                    match(input,SEQ,FOLLOW_SEQ_in_com457); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // FunChecker.g:258:10: ( com )*
                        loop8:
                        do {
                            int alt8=2;
                            int LA8_0 = input.LA(1);

                            if ( (LA8_0==ASSN||LA8_0==FOR||(LA8_0 >= IF && LA8_0 <= IFELSE)||LA8_0==PROCCALL||LA8_0==SEQ||LA8_0==WHILE) ) {
                                alt8=1;
                            }


                            switch (alt8) {
                        	case 1 :
                        	    // FunChecker.g:258:10: com
                        	    {
                        	    pushFollow(FOLLOW_com_in_com459);
                        	    com();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop8;
                            }
                        } while (true);


                        match(input, Token.UP, null); 
                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "com"



    // $ANTLR start "expr"
    // FunChecker.g:264:1: expr returns [Type type] : ( FALSE | TRUE | NUM | ID | ^( FUNCCALL ID t= expr ) | ^( EQ t1= expr t2= expr ) | ^( LT t1= expr t2= expr ) | ^( GT t1= expr t2= expr ) | ^( PLUS t1= expr t2= expr ) | ^( MINUS t1= expr t2= expr ) | ^( TIMES t1= expr t2= expr ) | ^( DIV t1= expr t2= expr ) | ^( NOT t= expr ) | NOACTUAL );
    public final Type expr() throws RecognitionException {
        Type type = null;


        CommonTree ID18=null;
        CommonTree ID19=null;
        CommonTree FUNCCALL20=null;
        CommonTree EQ21=null;
        CommonTree LT22=null;
        CommonTree GT23=null;
        CommonTree PLUS24=null;
        CommonTree MINUS25=null;
        CommonTree TIMES26=null;
        CommonTree DIV27=null;
        CommonTree NOT28=null;
        Type t =null;

        Type t1 =null;

        Type t2 =null;


        try {
            // FunChecker.g:265:2: ( FALSE | TRUE | NUM | ID | ^( FUNCCALL ID t= expr ) | ^( EQ t1= expr t2= expr ) | ^( LT t1= expr t2= expr ) | ^( GT t1= expr t2= expr ) | ^( PLUS t1= expr t2= expr ) | ^( MINUS t1= expr t2= expr ) | ^( TIMES t1= expr t2= expr ) | ^( DIV t1= expr t2= expr ) | ^( NOT t= expr ) | NOACTUAL )
            int alt10=14;
            switch ( input.LA(1) ) {
            case FALSE:
                {
                alt10=1;
                }
                break;
            case TRUE:
                {
                alt10=2;
                }
                break;
            case NUM:
                {
                alt10=3;
                }
                break;
            case ID:
                {
                alt10=4;
                }
                break;
            case FUNCCALL:
                {
                alt10=5;
                }
                break;
            case EQ:
                {
                alt10=6;
                }
                break;
            case LT:
                {
                alt10=7;
                }
                break;
            case GT:
                {
                alt10=8;
                }
                break;
            case PLUS:
                {
                alt10=9;
                }
                break;
            case MINUS:
                {
                alt10=10;
                }
                break;
            case TIMES:
                {
                alt10=11;
                }
                break;
            case DIV:
                {
                alt10=12;
                }
                break;
            case NOT:
                {
                alt10=13;
                }
                break;
            case NOACTUAL:
                {
                alt10=14;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }

            switch (alt10) {
                case 1 :
                    // FunChecker.g:265:4: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_expr482); 

                     type = Type.BOOL; 

                    }
                    break;
                case 2 :
                    // FunChecker.g:267:4: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_expr493); 

                     type = Type.BOOL; 

                    }
                    break;
                case 3 :
                    // FunChecker.g:269:4: NUM
                    {
                    match(input,NUM,FOLLOW_NUM_in_expr504); 

                     type = Type.INT; 

                    }
                    break;
                case 4 :
                    // FunChecker.g:271:4: ID
                    {
                    ID18=(CommonTree)match(input,ID,FOLLOW_ID_in_expr515); 

                     type = retrieve((ID18!=null?ID18.getText():null), ID18);
                    				

                    }
                    break;
                case 5 :
                    // FunChecker.g:274:4: ^( FUNCCALL ID t= expr )
                    {
                    FUNCCALL20=(CommonTree)match(input,FUNCCALL,FOLLOW_FUNCCALL_in_expr527); 

                    match(input, Token.DOWN, null); 
                    ID19=(CommonTree)match(input,ID,FOLLOW_ID_in_expr529); 

                    pushFollow(FOLLOW_expr_in_expr533);
                    t=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     Type result = checkCall(
                    				    (ID19!=null?ID19.getText():null), t, FUNCCALL20);
                    				  if (result.equiv(Type.VOID))
                    				    reportError(
                    					 "procedure should be non-void",
                    				      FUNCCALL20);
                    				  type = result;
                    				

                    }
                    break;
                case 6 :
                    // FunChecker.g:283:4: ^( EQ t1= expr t2= expr )
                    {
                    EQ21=(CommonTree)match(input,EQ,FOLLOW_EQ_in_expr546); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr550);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr554);
                    t2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     type = checkBinary(
                    				    COMPTYPE, t1, t2, EQ21); 

                    }
                    break;
                case 7 :
                    // FunChecker.g:286:4: ^( LT t1= expr t2= expr )
                    {
                    LT22=(CommonTree)match(input,LT,FOLLOW_LT_in_expr567); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr571);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr575);
                    t2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     type = checkBinary(
                    				    COMPTYPE, t1, t2, LT22); 

                    }
                    break;
                case 8 :
                    // FunChecker.g:289:4: ^( GT t1= expr t2= expr )
                    {
                    GT23=(CommonTree)match(input,GT,FOLLOW_GT_in_expr588); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr592);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr596);
                    t2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     type = checkBinary(
                    				    COMPTYPE, t1, t2, GT23); 

                    }
                    break;
                case 9 :
                    // FunChecker.g:292:4: ^( PLUS t1= expr t2= expr )
                    {
                    PLUS24=(CommonTree)match(input,PLUS,FOLLOW_PLUS_in_expr609); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr613);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr617);
                    t2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     type = checkBinary(
                    				    ARITHTYPE, t1, t2, PLUS24); 

                    }
                    break;
                case 10 :
                    // FunChecker.g:295:4: ^( MINUS t1= expr t2= expr )
                    {
                    MINUS25=(CommonTree)match(input,MINUS,FOLLOW_MINUS_in_expr630); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr634);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr638);
                    t2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     type = checkBinary(
                    				    ARITHTYPE, t1, t2, MINUS25); 

                    }
                    break;
                case 11 :
                    // FunChecker.g:298:4: ^( TIMES t1= expr t2= expr )
                    {
                    TIMES26=(CommonTree)match(input,TIMES,FOLLOW_TIMES_in_expr651); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr655);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr659);
                    t2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     type = checkBinary(
                    				    ARITHTYPE, t1, t2, TIMES26); 

                    }
                    break;
                case 12 :
                    // FunChecker.g:301:4: ^( DIV t1= expr t2= expr )
                    {
                    DIV27=(CommonTree)match(input,DIV,FOLLOW_DIV_in_expr672); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr676);
                    t1=expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr680);
                    t2=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     type = checkBinary(
                    				     ARITHTYPE, t1, t2, DIV27); 

                    }
                    break;
                case 13 :
                    // FunChecker.g:304:4: ^( NOT t= expr )
                    {
                    NOT28=(CommonTree)match(input,NOT,FOLLOW_NOT_in_expr693); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr697);
                    t=expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     type = checkUnary(NOTTYPE, t, NOT28); 

                    }
                    break;
                case 14 :
                    // FunChecker.g:306:4: NOACTUAL
                    {
                    match(input,NOACTUAL,FOLLOW_NOACTUAL_in_expr709); 

                     type = Type.VOID; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return type;
    }
    // $ANTLR end "expr"

    // Delegated rules


 

    public static final BitSet FOLLOW_PROG_in_program60 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_var_decl_in_program72 = new BitSet(new long[]{0x0000080200020000L});
    public static final BitSet FOLLOW_proc_decl_in_program79 = new BitSet(new long[]{0x0000000200020008L});
    public static final BitSet FOLLOW_PROC_in_proc_decl102 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_proc_decl108 = new BitSet(new long[]{0x0000000020010000L});
    public static final BitSet FOLLOW_formal_in_proc_decl122 = new BitSet(new long[]{0x0000184400608010L});
    public static final BitSet FOLLOW_var_decl_in_proc_decl134 = new BitSet(new long[]{0x0000184400608010L});
    public static final BitSet FOLLOW_com_in_proc_decl141 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_in_proc_decl154 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_proc_decl162 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_proc_decl168 = new BitSet(new long[]{0x0000000020010000L});
    public static final BitSet FOLLOW_formal_in_proc_decl182 = new BitSet(new long[]{0x0000184400608010L});
    public static final BitSet FOLLOW_var_decl_in_proc_decl194 = new BitSet(new long[]{0x0000184400608010L});
    public static final BitSet FOLLOW_com_in_proc_decl201 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_proc_decl209 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FORMAL_in_formal234 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_formal238 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_formal240 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOFORMAL_in_formal252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_var_decl270 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_var_decl274 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_var_decl276 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_var_decl280 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BOOL_in_type305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_type313 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSN_in_com331 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_com333 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_com337 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PROCCALL_in_com350 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_com352 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_com356 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_com369 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_com373 = new BitSet(new long[]{0x0000104400608010L});
    public static final BitSet FOLLOW_com_in_com375 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IFELSE_in_com388 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_com392 = new BitSet(new long[]{0x0000104400608010L});
    public static final BitSet FOLLOW_com_in_com394 = new BitSet(new long[]{0x0000104400608010L});
    public static final BitSet FOLLOW_com_in_com396 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WHILE_in_com409 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_com413 = new BitSet(new long[]{0x0000104400608010L});
    public static final BitSet FOLLOW_com_in_com415 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FOR_in_com428 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_com430 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ASSN_in_com432 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_com436 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_TO_in_com438 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_com442 = new BitSet(new long[]{0x0000104400608010L});
    public static final BitSet FOLLOW_com_in_com444 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SEQ_in_com457 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_com_in_com459 = new BitSet(new long[]{0x0000104400608018L});
    public static final BitSet FOLLOW_FALSE_in_expr482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_expr493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_expr504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_expr515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCCALL_in_expr527 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expr529 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr533 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQ_in_expr546 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr550 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr554 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expr567 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr571 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr575 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expr588 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr592 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr596 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PLUS_in_expr609 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr613 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr617 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MINUS_in_expr630 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr634 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr638 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TIMES_in_expr651 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr655 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr659 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIV_in_expr672 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr676 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr680 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOT_in_expr693 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr697 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOACTUAL_in_expr709 = new BitSet(new long[]{0x0000000000000002L});

}