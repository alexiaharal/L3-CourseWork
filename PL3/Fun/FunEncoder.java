// $ANTLR 3.4 .\\FunEncoder.g 2012-11-08 23:38:11

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class FunEncoder extends TreeParser {
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


    public FunEncoder(TreeNodeStream input) {
        this(input, new RecognizerSharedState());
    }
    public FunEncoder(TreeNodeStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return FunEncoder.tokenNames; }
    public String getGrammarFileName() { return ".\\FunEncoder.g"; }



    	private SVM obj = new SVM();

    	private int globalvaraddr = 0;
    	private int localvaraddr = 0;
    	private int currentLocale = Address.GLOBAL;

    	private SymbolTable<Address> addrTable =
    	   new SymbolTable<Address>();

    	private void predefine () {
    	// Add predefined procedures to the address table.
    		addrTable.put("read",
    		   new Address(SVM.READOFFSET, Address.CODE));
    		addrTable.put("write",
    		   new Address(SVM.WRITEOFFSET, Address.CODE));
    	}




    // $ANTLR start "program"
    // .\\FunEncoder.g:47:1: program returns [SVM objectprogram] : ^( PROG ( var_decl )* ( proc_decl )+ ) ;
    public final SVM program() throws RecognitionException {
        SVM objectprogram = null;


        try {
            // .\\FunEncoder.g:48:2: ( ^( PROG ( var_decl )* ( proc_decl )+ ) )
            // .\\FunEncoder.g:48:4: ^( PROG ( var_decl )* ( proc_decl )+ )
            {
            match(input,PROG,FOLLOW_PROG_in_program65); 

             predefine();
            				

            match(input, Token.DOWN, null); 
            // .\\FunEncoder.g:51:5: ( var_decl )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==VAR) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // .\\FunEncoder.g:51:5: var_decl
            	    {
            	    pushFollow(FOLLOW_var_decl_in_program77);
            	    var_decl();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


             int calladdr =
            				    obj.currentOffset();
            				  obj.emit12(SVM.CALL, 0);
            				  obj.emit1(SVM.HALT);
            				

            // .\\FunEncoder.g:57:5: ( proc_decl )+
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
            	    // .\\FunEncoder.g:57:5: proc_decl
            	    {
            	    pushFollow(FOLLOW_proc_decl_in_program90);
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


             int mainaddr =
            				    addrTable.get("main").offset;
            				  obj.patch12(calladdr, mainaddr);
            				  objectprogram = obj;
            				

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return objectprogram;
    }
    // $ANTLR end "program"



    // $ANTLR start "proc_decl"
    // .\\FunEncoder.g:68:1: proc_decl : ( ^( PROC ID formal ( var_decl )* com ) | ^( FUNC type ID formal ( var_decl )* com expr ) );
    public final void proc_decl() throws RecognitionException {
        CommonTree ID1=null;
        CommonTree ID2=null;

        try {
            // .\\FunEncoder.g:69:2: ( ^( PROC ID formal ( var_decl )* com ) | ^( FUNC type ID formal ( var_decl )* com expr ) )
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
                    // .\\FunEncoder.g:69:4: ^( PROC ID formal ( var_decl )* com )
                    {
                    match(input,PROC,FOLLOW_PROC_in_proc_decl113); 

                    match(input, Token.DOWN, null); 
                    ID1=(CommonTree)match(input,ID,FOLLOW_ID_in_proc_decl119); 

                     String id = (ID1!=null?ID1.getText():null);
                    				  Address procaddr = new Address(
                    				    obj.currentOffset(), Address.CODE);
                    				  addrTable.put(id, procaddr);
                    				  addrTable.enterLocalScope();
                    				  currentLocale = Address.LOCAL;
                    				  localvaraddr = 2;
                    				  // ... allows 2 words for link data
                    				

                    pushFollow(FOLLOW_formal_in_proc_decl131);
                    formal();

                    state._fsp--;


                    // .\\FunEncoder.g:81:5: ( var_decl )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==VAR) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // .\\FunEncoder.g:81:5: var_decl
                    	    {
                    	    pushFollow(FOLLOW_var_decl_in_proc_decl137);
                    	    var_decl();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    pushFollow(FOLLOW_com_in_proc_decl144);
                    com();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     obj.emit11(SVM.RETURN, 0);
                    				  addrTable.exitLocalScope();
                    				  currentLocale = Address.GLOBAL;
                    				

                    }
                    break;
                case 2 :
                    // .\\FunEncoder.g:87:4: ^( FUNC type ID formal ( var_decl )* com expr )
                    {
                    match(input,FUNC,FOLLOW_FUNC_in_proc_decl157); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_in_proc_decl163);
                    type();

                    state._fsp--;


                    ID2=(CommonTree)match(input,ID,FOLLOW_ID_in_proc_decl169); 

                     String id = (ID2!=null?ID2.getText():null);
                    				  Address procaddr = new Address(
                    				    obj.currentOffset(), Address.CODE);
                    				  addrTable.put(id, procaddr);
                    				  addrTable.enterLocalScope();
                    				  currentLocale = Address.LOCAL;
                    				  localvaraddr = 2;
                    				  // ... allows 2 words for link data
                    				

                    pushFollow(FOLLOW_formal_in_proc_decl181);
                    formal();

                    state._fsp--;


                    // .\\FunEncoder.g:100:5: ( var_decl )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==VAR) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // .\\FunEncoder.g:100:5: var_decl
                    	    {
                    	    pushFollow(FOLLOW_var_decl_in_proc_decl187);
                    	    var_decl();

                    	    state._fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    pushFollow(FOLLOW_com_in_proc_decl194);
                    com();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_proc_decl200);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     obj.emit11(SVM.RETURN, 1);
                    				  addrTable.exitLocalScope();
                    				  currentLocale = Address.GLOBAL;
                    				

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
    // .\\FunEncoder.g:109:1: formal : ( ^( FORMAL type ID ) | NOFORMAL );
    public final void formal() throws RecognitionException {
        CommonTree ID3=null;

        try {
            // .\\FunEncoder.g:110:2: ( ^( FORMAL type ID ) | NOFORMAL )
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
                    // .\\FunEncoder.g:110:4: ^( FORMAL type ID )
                    {
                    match(input,FORMAL,FOLLOW_FORMAL_in_formal219); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_type_in_formal225);
                    type();

                    state._fsp--;


                    ID3=(CommonTree)match(input,ID,FOLLOW_ID_in_formal231); 

                    match(input, Token.UP, null); 


                     String id = (ID3!=null?ID3.getText():null);
                    				  addrTable.put(id, new Address(
                    				    localvaraddr++, Address.LOCAL));
                    				  obj.emit11(SVM.COPYARG, 1); 
                    				

                    }
                    break;
                case 2 :
                    // .\\FunEncoder.g:118:4: NOFORMAL
                    {
                    match(input,NOFORMAL,FOLLOW_NOFORMAL_in_formal243); 

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
    // $ANTLR end "formal"



    // $ANTLR start "var_decl"
    // .\\FunEncoder.g:121:1: var_decl : ^( VAR type ID expr ) ;
    public final void var_decl() throws RecognitionException {
        CommonTree ID4=null;

        try {
            // .\\FunEncoder.g:122:2: ( ^( VAR type ID expr ) )
            // .\\FunEncoder.g:122:4: ^( VAR type ID expr )
            {
            match(input,VAR,FOLLOW_VAR_in_var_decl255); 

            match(input, Token.DOWN, null); 
            pushFollow(FOLLOW_type_in_var_decl261);
            type();

            state._fsp--;


            ID4=(CommonTree)match(input,ID,FOLLOW_ID_in_var_decl267); 

            pushFollow(FOLLOW_expr_in_var_decl273);
            expr();

            state._fsp--;


            match(input, Token.UP, null); 


             String id = (ID4!=null?ID4.getText():null);
            				  switch (currentLocale) {
            				    case Address.LOCAL:
            				      addrTable.put(id, new Address(
            				        localvaraddr++, Address.LOCAL));
            				      break;
            				    case Address.GLOBAL:
            				      addrTable.put(id, new Address(
            				        globalvaraddr++, Address.GLOBAL));
            				  }
            				

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
    // .\\FunEncoder.g:139:1: type : ( BOOL | INT );
    public final void type() throws RecognitionException {
        try {
            // .\\FunEncoder.g:140:2: ( BOOL | INT )
            // .\\FunEncoder.g:
            {
            if ( input.LA(1)==BOOL||input.LA(1)==INT ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


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
    // $ANTLR end "type"



    // $ANTLR start "com"
    // .\\FunEncoder.g:147:1: com : ( ^( ASSN ID expr ) | ^( PROCCALL ID expr ) | ^( IF expr com ) | ^( IFELSE expr com com ) | ^( WHILE expr com ) | ^( SEQ ( com )* ) );
    public final void com() throws RecognitionException {
        CommonTree ID5=null;
        CommonTree ID6=null;

        try {
            // .\\FunEncoder.g:148:2: ( ^( ASSN ID expr ) | ^( PROCCALL ID expr ) | ^( IF expr com ) | ^( IFELSE expr com com ) | ^( WHILE expr com ) | ^( SEQ ( com )* ) )
            int alt8=6;
            switch ( input.LA(1) ) {
            case ASSN:
                {
                alt8=1;
                }
                break;
            case PROCCALL:
                {
                alt8=2;
                }
                break;
            case IF:
                {
                alt8=3;
                }
                break;
            case IFELSE:
                {
                alt8=4;
                }
                break;
            case WHILE:
                {
                alt8=5;
                }
                break;
            case SEQ:
                {
                alt8=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;

            }

            switch (alt8) {
                case 1 :
                    // .\\FunEncoder.g:148:4: ^( ASSN ID expr )
                    {
                    match(input,ASSN,FOLLOW_ASSN_in_com311); 

                    match(input, Token.DOWN, null); 
                    ID5=(CommonTree)match(input,ID,FOLLOW_ID_in_com313); 

                    pushFollow(FOLLOW_expr_in_com315);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     String id = (ID5!=null?ID5.getText():null);
                    				  Address varaddr = addrTable.get(id);
                    				  switch (varaddr.locale) {
                    				    case Address.GLOBAL:
                    				      obj.emit12(SVM.STOREG,
                    				        varaddr.offset);
                    				      break;
                    				    case Address.LOCAL:
                    				      obj.emit12(SVM.STOREL,
                    				        varaddr.offset);
                    				  }
                    				

                    }
                    break;
                case 2 :
                    // .\\FunEncoder.g:161:4: ^( PROCCALL ID expr )
                    {
                    match(input,PROCCALL,FOLLOW_PROCCALL_in_com328); 

                    match(input, Token.DOWN, null); 
                    ID6=(CommonTree)match(input,ID,FOLLOW_ID_in_com330); 

                    pushFollow(FOLLOW_expr_in_com332);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     String id = (ID6!=null?ID6.getText():null);
                    				  Address procaddr = addrTable.get(id);
                    				  // Assume procaddr.locale == CODE.
                    				  obj.emit12(SVM.CALL,
                    				    procaddr.offset);
                    				

                    }
                    break;
                case 3 :
                    // .\\FunEncoder.g:168:4: ^( IF expr com )
                    {
                    match(input,IF,FOLLOW_IF_in_com345); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_com351);
                    expr();

                    state._fsp--;


                     int condaddr = obj.currentOffset();
                    				  obj.emit12(SVM.JUMPF, 0);
                    				

                    pushFollow(FOLLOW_com_in_com363);
                    com();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     int exitaddr = obj.currentOffset();
                    				  obj.patch12(condaddr, exitaddr);
                    				

                    }
                    break;
                case 4 :
                    // .\\FunEncoder.g:177:4: ^( IFELSE expr com com )
                    {
                    match(input,IFELSE,FOLLOW_IFELSE_in_com376); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_com382);
                    expr();

                    state._fsp--;


                     int condaddr = obj.currentOffset();
                    				  obj.emit12(SVM.JUMPF, 0);
                    				

                    pushFollow(FOLLOW_com_in_com394);
                    com();

                    state._fsp--;


                     int jumpaddr = obj.currentOffset();
                    				  obj.emit12(SVM.JUMP, 0);
                    				  int elseaddr = obj.currentOffset();
                    				  obj.patch12(condaddr, elseaddr);
                    				

                    pushFollow(FOLLOW_com_in_com406);
                    com();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     int exitaddr = obj.currentOffset();
                    				  obj.patch12(jumpaddr, exitaddr);
                    				

                    }
                    break;
                case 5 :
                    // .\\FunEncoder.g:192:4: ^( WHILE expr com )
                    {
                    match(input,WHILE,FOLLOW_WHILE_in_com419); 

                     int startaddr = obj.currentOffset();
                    				

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_com432);
                    expr();

                    state._fsp--;


                     int condaddr = obj.currentOffset();
                    				  obj.emit12(SVM.JUMPF, 0);
                    				

                    pushFollow(FOLLOW_com_in_com445);
                    com();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     obj.emit12(SVM.JUMP, startaddr);
                    				  int exitaddr = obj.currentOffset();
                    				  obj.patch12(condaddr, exitaddr);
                    				

                    }
                    break;
                case 6 :
                    // .\\FunEncoder.g:204:4: ^( SEQ ( com )* )
                    {
                    match(input,SEQ,FOLLOW_SEQ_in_com458); 

                    if ( input.LA(1)==Token.DOWN ) {
                        match(input, Token.DOWN, null); 
                        // .\\FunEncoder.g:204:10: ( com )*
                        loop7:
                        do {
                            int alt7=2;
                            int LA7_0 = input.LA(1);

                            if ( (LA7_0==ASSN||(LA7_0 >= IF && LA7_0 <= IFELSE)||LA7_0==PROCCALL||LA7_0==SEQ||LA7_0==WHILE) ) {
                                alt7=1;
                            }


                            switch (alt7) {
                        	case 1 :
                        	    // .\\FunEncoder.g:204:10: com
                        	    {
                        	    pushFollow(FOLLOW_com_in_com460);
                        	    com();

                        	    state._fsp--;


                        	    }
                        	    break;

                        	default :
                        	    break loop7;
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
    // .\\FunEncoder.g:210:1: expr : ( FALSE | TRUE | NUM | ID | ^( FUNCCALL ID expr ) | ^( EQ expr expr ) | ^( LT expr expr ) | ^( GT expr expr ) | ^( PLUS expr expr ) | ^( MINUS expr expr ) | ^( TIMES expr expr ) | ^( DIV expr expr ) | ^( NOT expr ) | NOACTUAL );
    public final void expr() throws RecognitionException {
        CommonTree NUM7=null;
        CommonTree ID8=null;
        CommonTree ID9=null;

        try {
            // .\\FunEncoder.g:211:2: ( FALSE | TRUE | NUM | ID | ^( FUNCCALL ID expr ) | ^( EQ expr expr ) | ^( LT expr expr ) | ^( GT expr expr ) | ^( PLUS expr expr ) | ^( MINUS expr expr ) | ^( TIMES expr expr ) | ^( DIV expr expr ) | ^( NOT expr ) | NOACTUAL )
            int alt9=14;
            switch ( input.LA(1) ) {
            case FALSE:
                {
                alt9=1;
                }
                break;
            case TRUE:
                {
                alt9=2;
                }
                break;
            case NUM:
                {
                alt9=3;
                }
                break;
            case ID:
                {
                alt9=4;
                }
                break;
            case FUNCCALL:
                {
                alt9=5;
                }
                break;
            case EQ:
                {
                alt9=6;
                }
                break;
            case LT:
                {
                alt9=7;
                }
                break;
            case GT:
                {
                alt9=8;
                }
                break;
            case PLUS:
                {
                alt9=9;
                }
                break;
            case MINUS:
                {
                alt9=10;
                }
                break;
            case TIMES:
                {
                alt9=11;
                }
                break;
            case DIV:
                {
                alt9=12;
                }
                break;
            case NOT:
                {
                alt9=13;
                }
                break;
            case NOACTUAL:
                {
                alt9=14;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }

            switch (alt9) {
                case 1 :
                    // .\\FunEncoder.g:211:4: FALSE
                    {
                    match(input,FALSE,FOLLOW_FALSE_in_expr476); 

                     obj.emit12(SVM.LOADLIT, 0); 

                    }
                    break;
                case 2 :
                    // .\\FunEncoder.g:213:4: TRUE
                    {
                    match(input,TRUE,FOLLOW_TRUE_in_expr487); 

                     obj.emit12(SVM.LOADLIT, 1); 

                    }
                    break;
                case 3 :
                    // .\\FunEncoder.g:215:4: NUM
                    {
                    NUM7=(CommonTree)match(input,NUM,FOLLOW_NUM_in_expr498); 

                     int value =
                    				    Integer.parseInt((NUM7!=null?NUM7.getText():null));
                    				  obj.emit12(SVM.LOADLIT, value);
                    				

                    }
                    break;
                case 4 :
                    // .\\FunEncoder.g:220:4: ID
                    {
                    ID8=(CommonTree)match(input,ID,FOLLOW_ID_in_expr509); 

                     String id = (ID8!=null?ID8.getText():null);
                    				  Address varaddr = addrTable.get(id);
                    				  switch (varaddr.locale) {
                    				    case Address.GLOBAL:
                    				      obj.emit12(SVM.LOADG,
                    				        varaddr.offset);
                    				      break;
                    				    case Address.LOCAL:
                    				      obj.emit12(SVM.LOADL,
                    				        varaddr.offset);
                    				  }
                    				

                    }
                    break;
                case 5 :
                    // .\\FunEncoder.g:233:4: ^( FUNCCALL ID expr )
                    {
                    match(input,FUNCCALL,FOLLOW_FUNCCALL_in_expr521); 

                    match(input, Token.DOWN, null); 
                    ID9=(CommonTree)match(input,ID,FOLLOW_ID_in_expr523); 

                    pushFollow(FOLLOW_expr_in_expr525);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     String id = (ID9!=null?ID9.getText():null);
                    				  Address funcaddr = addrTable.get(id);
                    				  // Assume funcaddr.locale == CODE ...
                    				  obj.emit12(SVM.CALL,
                    				    funcaddr.offset);
                    				

                    }
                    break;
                case 6 :
                    // .\\FunEncoder.g:240:4: ^( EQ expr expr )
                    {
                    match(input,EQ,FOLLOW_EQ_in_expr538); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr540);
                    expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr542);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     obj.emit1(SVM.CMPEQ); 

                    }
                    break;
                case 7 :
                    // .\\FunEncoder.g:242:4: ^( LT expr expr )
                    {
                    match(input,LT,FOLLOW_LT_in_expr555); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr557);
                    expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr559);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     obj.emit1(SVM.CMPLT); 

                    }
                    break;
                case 8 :
                    // .\\FunEncoder.g:244:4: ^( GT expr expr )
                    {
                    match(input,GT,FOLLOW_GT_in_expr572); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr574);
                    expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr576);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     obj.emit1(SVM.CMPGT); 

                    }
                    break;
                case 9 :
                    // .\\FunEncoder.g:246:4: ^( PLUS expr expr )
                    {
                    match(input,PLUS,FOLLOW_PLUS_in_expr589); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr591);
                    expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr593);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     obj.emit1(SVM.ADD); 

                    }
                    break;
                case 10 :
                    // .\\FunEncoder.g:248:4: ^( MINUS expr expr )
                    {
                    match(input,MINUS,FOLLOW_MINUS_in_expr606); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr608);
                    expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr610);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     obj.emit1(SVM.SUB); 

                    }
                    break;
                case 11 :
                    // .\\FunEncoder.g:250:4: ^( TIMES expr expr )
                    {
                    match(input,TIMES,FOLLOW_TIMES_in_expr623); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr625);
                    expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr627);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     obj.emit1(SVM.MUL); 

                    }
                    break;
                case 12 :
                    // .\\FunEncoder.g:252:4: ^( DIV expr expr )
                    {
                    match(input,DIV,FOLLOW_DIV_in_expr640); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr642);
                    expr();

                    state._fsp--;


                    pushFollow(FOLLOW_expr_in_expr644);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     obj.emit1(SVM.DIV); 

                    }
                    break;
                case 13 :
                    // .\\FunEncoder.g:254:4: ^( NOT expr )
                    {
                    match(input,NOT,FOLLOW_NOT_in_expr657); 

                    match(input, Token.DOWN, null); 
                    pushFollow(FOLLOW_expr_in_expr659);
                    expr();

                    state._fsp--;


                    match(input, Token.UP, null); 


                     obj.emit1(SVM.INV); 

                    }
                    break;
                case 14 :
                    // .\\FunEncoder.g:256:4: NOACTUAL
                    {
                    match(input,NOACTUAL,FOLLOW_NOACTUAL_in_expr671); 

                     

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
    // $ANTLR end "expr"

    // Delegated rules


 

    public static final BitSet FOLLOW_PROG_in_program65 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_var_decl_in_program77 = new BitSet(new long[]{0x0000080200020000L});
    public static final BitSet FOLLOW_proc_decl_in_program90 = new BitSet(new long[]{0x0000000200020008L});
    public static final BitSet FOLLOW_PROC_in_proc_decl113 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_proc_decl119 = new BitSet(new long[]{0x0000000020010000L});
    public static final BitSet FOLLOW_formal_in_proc_decl131 = new BitSet(new long[]{0x0000184400600010L});
    public static final BitSet FOLLOW_var_decl_in_proc_decl137 = new BitSet(new long[]{0x0000184400600010L});
    public static final BitSet FOLLOW_com_in_proc_decl144 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_in_proc_decl157 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_proc_decl163 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_proc_decl169 = new BitSet(new long[]{0x0000000020010000L});
    public static final BitSet FOLLOW_formal_in_proc_decl181 = new BitSet(new long[]{0x0000184400600010L});
    public static final BitSet FOLLOW_var_decl_in_proc_decl187 = new BitSet(new long[]{0x0000184400600010L});
    public static final BitSet FOLLOW_com_in_proc_decl194 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_proc_decl200 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FORMAL_in_formal219 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_formal225 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_formal231 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOFORMAL_in_formal243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_var_decl255 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_type_in_var_decl261 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_var_decl267 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_var_decl273 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ASSN_in_com311 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_com313 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_com315 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PROCCALL_in_com328 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_com330 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_com332 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IF_in_com345 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_com351 = new BitSet(new long[]{0x0000104400600010L});
    public static final BitSet FOLLOW_com_in_com363 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IFELSE_in_com376 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_com382 = new BitSet(new long[]{0x0000104400600010L});
    public static final BitSet FOLLOW_com_in_com394 = new BitSet(new long[]{0x0000104400600010L});
    public static final BitSet FOLLOW_com_in_com406 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WHILE_in_com419 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_com432 = new BitSet(new long[]{0x0000104400600010L});
    public static final BitSet FOLLOW_com_in_com445 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_SEQ_in_com458 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_com_in_com460 = new BitSet(new long[]{0x0000104400600018L});
    public static final BitSet FOLLOW_FALSE_in_expr476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_expr487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_expr498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_expr509 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCCALL_in_expr521 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ID_in_expr523 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr525 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_EQ_in_expr538 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr540 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr542 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LT_in_expr555 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr557 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr559 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GT_in_expr572 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr574 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr576 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PLUS_in_expr589 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr591 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr593 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_MINUS_in_expr606 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr608 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr610 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_TIMES_in_expr623 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr625 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr627 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DIV_in_expr640 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr642 = new BitSet(new long[]{0x00000501DC1C6200L});
    public static final BitSet FOLLOW_expr_in_expr644 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOT_in_expr657 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_expr_in_expr659 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NOACTUAL_in_expr671 = new BitSet(new long[]{0x0000000000000002L});

}