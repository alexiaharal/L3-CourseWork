// $ANTLR 3.4 Fun.g 2012-11-09 00:45:17

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

import org.antlr.runtime.tree.*;


@SuppressWarnings({"all", "warnings", "unchecked"})
public class FunParser extends Parser {
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
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public FunParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public FunParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

protected TreeAdaptor adaptor = new CommonTreeAdaptor();

public void setTreeAdaptor(TreeAdaptor adaptor) {
    this.adaptor = adaptor;
}
public TreeAdaptor getTreeAdaptor() {
    return adaptor;
}
    public String[] getTokenNames() { return FunParser.tokenNames; }
    public String getGrammarFileName() { return "Fun.g"; }


    public static class program_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "program"
    // Fun.g:37:1: program : ( var_decl )* ( proc_decl )+ EOF -> ^( PROG ( var_decl )* ( proc_decl )+ ) ;
    public final FunParser.program_return program() throws RecognitionException {
        FunParser.program_return retval = new FunParser.program_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token EOF3=null;
        FunParser.var_decl_return var_decl1 =null;

        FunParser.proc_decl_return proc_decl2 =null;


        CommonTree EOF3_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_proc_decl=new RewriteRuleSubtreeStream(adaptor,"rule proc_decl");
        RewriteRuleSubtreeStream stream_var_decl=new RewriteRuleSubtreeStream(adaptor,"rule var_decl");
        try {
            // Fun.g:38:2: ( ( var_decl )* ( proc_decl )+ EOF -> ^( PROG ( var_decl )* ( proc_decl )+ ) )
            // Fun.g:38:4: ( var_decl )* ( proc_decl )+ EOF
            {
            // Fun.g:38:4: ( var_decl )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==BOOL||LA1_0==INT) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // Fun.g:38:4: var_decl
            	    {
            	    pushFollow(FOLLOW_var_decl_in_program99);
            	    var_decl1=var_decl();

            	    state._fsp--;

            	    stream_var_decl.add(var_decl1.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            // Fun.g:38:14: ( proc_decl )+
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
            	    // Fun.g:38:14: proc_decl
            	    {
            	    pushFollow(FOLLOW_proc_decl_in_program102);
            	    proc_decl2=proc_decl();

            	    state._fsp--;

            	    stream_proc_decl.add(proc_decl2.getTree());

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


            EOF3=(Token)match(input,EOF,FOLLOW_EOF_in_program105);  
            stream_EOF.add(EOF3);


            // AST REWRITE
            // elements: proc_decl, var_decl
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 38:31: -> ^( PROG ( var_decl )* ( proc_decl )+ )
            {
                // Fun.g:38:34: ^( PROG ( var_decl )* ( proc_decl )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(PROG, "PROG")
                , root_1);

                // Fun.g:39:35: ( var_decl )*
                while ( stream_var_decl.hasNext() ) {
                    adaptor.addChild(root_1, stream_var_decl.nextTree());

                }
                stream_var_decl.reset();

                if ( !(stream_proc_decl.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_proc_decl.hasNext() ) {
                    adaptor.addChild(root_1, stream_proc_decl.nextTree());

                }
                stream_proc_decl.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "program"


    public static class proc_decl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "proc_decl"
    // Fun.g:46:1: proc_decl : ( PROC ID LPAR formal RPAR COLON ( var_decl )* seq_com DOT -> ^( PROC ID formal ( var_decl )* seq_com ) | FUNC type ID LPAR formal RPAR COLON ( var_decl )* seq_com RETURN expr DOT -> ^( FUNC type ID formal ( var_decl )* seq_com expr ) );
    public final FunParser.proc_decl_return proc_decl() throws RecognitionException {
        FunParser.proc_decl_return retval = new FunParser.proc_decl_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token PROC4=null;
        Token ID5=null;
        Token LPAR6=null;
        Token RPAR8=null;
        Token COLON9=null;
        Token DOT12=null;
        Token FUNC13=null;
        Token ID15=null;
        Token LPAR16=null;
        Token RPAR18=null;
        Token COLON19=null;
        Token RETURN22=null;
        Token DOT24=null;
        FunParser.formal_return formal7 =null;

        FunParser.var_decl_return var_decl10 =null;

        FunParser.seq_com_return seq_com11 =null;

        FunParser.type_return type14 =null;

        FunParser.formal_return formal17 =null;

        FunParser.var_decl_return var_decl20 =null;

        FunParser.seq_com_return seq_com21 =null;

        FunParser.expr_return expr23 =null;


        CommonTree PROC4_tree=null;
        CommonTree ID5_tree=null;
        CommonTree LPAR6_tree=null;
        CommonTree RPAR8_tree=null;
        CommonTree COLON9_tree=null;
        CommonTree DOT12_tree=null;
        CommonTree FUNC13_tree=null;
        CommonTree ID15_tree=null;
        CommonTree LPAR16_tree=null;
        CommonTree RPAR18_tree=null;
        CommonTree COLON19_tree=null;
        CommonTree RETURN22_tree=null;
        CommonTree DOT24_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_RPAR=new RewriteRuleTokenStream(adaptor,"token RPAR");
        RewriteRuleTokenStream stream_LPAR=new RewriteRuleTokenStream(adaptor,"token LPAR");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_FUNC=new RewriteRuleTokenStream(adaptor,"token FUNC");
        RewriteRuleTokenStream stream_RETURN=new RewriteRuleTokenStream(adaptor,"token RETURN");
        RewriteRuleTokenStream stream_PROC=new RewriteRuleTokenStream(adaptor,"token PROC");
        RewriteRuleSubtreeStream stream_formal=new RewriteRuleSubtreeStream(adaptor,"rule formal");
        RewriteRuleSubtreeStream stream_seq_com=new RewriteRuleSubtreeStream(adaptor,"rule seq_com");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        RewriteRuleSubtreeStream stream_var_decl=new RewriteRuleSubtreeStream(adaptor,"rule var_decl");
        try {
            // Fun.g:47:2: ( PROC ID LPAR formal RPAR COLON ( var_decl )* seq_com DOT -> ^( PROC ID formal ( var_decl )* seq_com ) | FUNC type ID LPAR formal RPAR COLON ( var_decl )* seq_com RETURN expr DOT -> ^( FUNC type ID formal ( var_decl )* seq_com expr ) )
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
                    // Fun.g:47:4: PROC ID LPAR formal RPAR COLON ( var_decl )* seq_com DOT
                    {
                    PROC4=(Token)match(input,PROC,FOLLOW_PROC_in_proc_decl202);  
                    stream_PROC.add(PROC4);


                    ID5=(Token)match(input,ID,FOLLOW_ID_in_proc_decl204);  
                    stream_ID.add(ID5);


                    LPAR6=(Token)match(input,LPAR,FOLLOW_LPAR_in_proc_decl210);  
                    stream_LPAR.add(LPAR6);


                    pushFollow(FOLLOW_formal_in_proc_decl212);
                    formal7=formal();

                    state._fsp--;

                    stream_formal.add(formal7.getTree());

                    RPAR8=(Token)match(input,RPAR,FOLLOW_RPAR_in_proc_decl214);  
                    stream_RPAR.add(RPAR8);


                    COLON9=(Token)match(input,COLON,FOLLOW_COLON_in_proc_decl216);  
                    stream_COLON.add(COLON9);


                    // Fun.g:49:5: ( var_decl )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==BOOL||LA3_0==INT) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // Fun.g:49:5: var_decl
                    	    {
                    	    pushFollow(FOLLOW_var_decl_in_proc_decl222);
                    	    var_decl10=var_decl();

                    	    state._fsp--;

                    	    stream_var_decl.add(var_decl10.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    pushFollow(FOLLOW_seq_com_in_proc_decl225);
                    seq_com11=seq_com();

                    state._fsp--;

                    stream_seq_com.add(seq_com11.getTree());

                    DOT12=(Token)match(input,DOT,FOLLOW_DOT_in_proc_decl227);  
                    stream_DOT.add(DOT12);


                    // AST REWRITE
                    // elements: var_decl, ID, formal, seq_com, PROC
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 49:30: -> ^( PROC ID formal ( var_decl )* seq_com )
                    {
                        // Fun.g:49:33: ^( PROC ID formal ( var_decl )* seq_com )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_PROC.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_1, stream_formal.nextTree());

                        // Fun.g:51:35: ( var_decl )*
                        while ( stream_var_decl.hasNext() ) {
                            adaptor.addChild(root_1, stream_var_decl.nextTree());

                        }
                        stream_var_decl.reset();

                        adaptor.addChild(root_1, stream_seq_com.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // Fun.g:52:4: FUNC type ID LPAR formal RPAR COLON ( var_decl )* seq_com RETURN expr DOT
                    {
                    FUNC13=(Token)match(input,FUNC,FOLLOW_FUNC_in_proc_decl319);  
                    stream_FUNC.add(FUNC13);


                    pushFollow(FOLLOW_type_in_proc_decl321);
                    type14=type();

                    state._fsp--;

                    stream_type.add(type14.getTree());

                    ID15=(Token)match(input,ID,FOLLOW_ID_in_proc_decl323);  
                    stream_ID.add(ID15);


                    LPAR16=(Token)match(input,LPAR,FOLLOW_LPAR_in_proc_decl329);  
                    stream_LPAR.add(LPAR16);


                    pushFollow(FOLLOW_formal_in_proc_decl331);
                    formal17=formal();

                    state._fsp--;

                    stream_formal.add(formal17.getTree());

                    RPAR18=(Token)match(input,RPAR,FOLLOW_RPAR_in_proc_decl333);  
                    stream_RPAR.add(RPAR18);


                    COLON19=(Token)match(input,COLON,FOLLOW_COLON_in_proc_decl335);  
                    stream_COLON.add(COLON19);


                    // Fun.g:54:5: ( var_decl )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==BOOL||LA4_0==INT) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // Fun.g:54:5: var_decl
                    	    {
                    	    pushFollow(FOLLOW_var_decl_in_proc_decl341);
                    	    var_decl20=var_decl();

                    	    state._fsp--;

                    	    stream_var_decl.add(var_decl20.getTree());

                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);


                    pushFollow(FOLLOW_seq_com_in_proc_decl344);
                    seq_com21=seq_com();

                    state._fsp--;

                    stream_seq_com.add(seq_com21.getTree());

                    RETURN22=(Token)match(input,RETURN,FOLLOW_RETURN_in_proc_decl350);  
                    stream_RETURN.add(RETURN22);


                    pushFollow(FOLLOW_expr_in_proc_decl352);
                    expr23=expr();

                    state._fsp--;

                    stream_expr.add(expr23.getTree());

                    DOT24=(Token)match(input,DOT,FOLLOW_DOT_in_proc_decl354);  
                    stream_DOT.add(DOT24);


                    // AST REWRITE
                    // elements: var_decl, ID, expr, formal, seq_com, type, FUNC
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 55:30: -> ^( FUNC type ID formal ( var_decl )* seq_com expr )
                    {
                        // Fun.g:55:33: ^( FUNC type ID formal ( var_decl )* seq_com expr )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_FUNC.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_type.nextTree());

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_1, stream_formal.nextTree());

                        // Fun.g:57:35: ( var_decl )*
                        while ( stream_var_decl.hasNext() ) {
                            adaptor.addChild(root_1, stream_var_decl.nextTree());

                        }
                        stream_var_decl.reset();

                        adaptor.addChild(root_1, stream_seq_com.nextTree());

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "proc_decl"


    public static class formal_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "formal"
    // Fun.g:61:1: formal : ( type ID -> ^( FORMAL type ID ) | -> NOFORMAL );
    public final FunParser.formal_return formal() throws RecognitionException {
        FunParser.formal_return retval = new FunParser.formal_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID26=null;
        FunParser.type_return type25 =null;


        CommonTree ID26_tree=null;
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // Fun.g:62:2: ( type ID -> ^( FORMAL type ID ) | -> NOFORMAL )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==BOOL||LA6_0==INT) ) {
                alt6=1;
            }
            else if ( (LA6_0==RPAR) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;

            }
            switch (alt6) {
                case 1 :
                    // Fun.g:62:4: type ID
                    {
                    pushFollow(FOLLOW_type_in_formal496);
                    type25=type();

                    state._fsp--;

                    stream_type.add(type25.getTree());

                    ID26=(Token)match(input,ID,FOLLOW_ID_in_formal498);  
                    stream_ID.add(ID26);


                    // AST REWRITE
                    // elements: type, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 62:31: -> ^( FORMAL type ID )
                    {
                        // Fun.g:62:34: ^( FORMAL type ID )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(FORMAL, "FORMAL")
                        , root_1);

                        adaptor.addChild(root_1, stream_type.nextTree());

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // Fun.g:63:31: 
                    {
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 63:31: -> NOFORMAL
                    {
                        adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(NOFORMAL, "NOFORMAL")
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "formal"


    public static class var_decl_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "var_decl"
    // Fun.g:66:1: var_decl : type ID ASSN expr -> ^( VAR type ID expr ) ;
    public final FunParser.var_decl_return var_decl() throws RecognitionException {
        FunParser.var_decl_return retval = new FunParser.var_decl_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID28=null;
        Token ASSN29=null;
        FunParser.type_return type27 =null;

        FunParser.expr_return expr30 =null;


        CommonTree ID28_tree=null;
        CommonTree ASSN29_tree=null;
        RewriteRuleTokenStream stream_ASSN=new RewriteRuleTokenStream(adaptor,"token ASSN");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        RewriteRuleSubtreeStream stream_type=new RewriteRuleSubtreeStream(adaptor,"rule type");
        try {
            // Fun.g:67:2: ( type ID ASSN expr -> ^( VAR type ID expr ) )
            // Fun.g:67:4: type ID ASSN expr
            {
            pushFollow(FOLLOW_type_in_var_decl572);
            type27=type();

            state._fsp--;

            stream_type.add(type27.getTree());

            ID28=(Token)match(input,ID,FOLLOW_ID_in_var_decl574);  
            stream_ID.add(ID28);


            ASSN29=(Token)match(input,ASSN,FOLLOW_ASSN_in_var_decl576);  
            stream_ASSN.add(ASSN29);


            pushFollow(FOLLOW_expr_in_var_decl578);
            expr30=expr();

            state._fsp--;

            stream_expr.add(expr30.getTree());

            // AST REWRITE
            // elements: expr, ID, type
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 67:31: -> ^( VAR type ID expr )
            {
                // Fun.g:67:34: ^( VAR type ID expr )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(VAR, "VAR")
                , root_1);

                adaptor.addChild(root_1, stream_type.nextTree());

                adaptor.addChild(root_1, 
                stream_ID.nextNode()
                );

                adaptor.addChild(root_1, stream_expr.nextTree());

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "var_decl"


    public static class type_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "type"
    // Fun.g:70:1: type : ( BOOL -> BOOL | INT -> INT );
    public final FunParser.type_return type() throws RecognitionException {
        FunParser.type_return retval = new FunParser.type_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token BOOL31=null;
        Token INT32=null;

        CommonTree BOOL31_tree=null;
        CommonTree INT32_tree=null;
        RewriteRuleTokenStream stream_INT=new RewriteRuleTokenStream(adaptor,"token INT");
        RewriteRuleTokenStream stream_BOOL=new RewriteRuleTokenStream(adaptor,"token BOOL");

        try {
            // Fun.g:71:2: ( BOOL -> BOOL | INT -> INT )
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
                    // Fun.g:71:4: BOOL
                    {
                    BOOL31=(Token)match(input,BOOL,FOLLOW_BOOL_in_type610);  
                    stream_BOOL.add(BOOL31);


                    // AST REWRITE
                    // elements: BOOL
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 71:31: -> BOOL
                    {
                        adaptor.addChild(root_0, 
                        stream_BOOL.nextNode()
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // Fun.g:72:4: INT
                    {
                    INT32=(Token)match(input,INT,FOLLOW_INT_in_type641);  
                    stream_INT.add(INT32);


                    // AST REWRITE
                    // elements: INT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 72:31: -> INT
                    {
                        adaptor.addChild(root_0, 
                        stream_INT.nextNode()
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "type"


    public static class com_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "com"
    // Fun.g:78:1: com : ( ID ASSN expr -> ^( ASSN ID expr ) | ID LPAR actual RPAR -> ^( PROCCALL ID actual ) | IF expr COLON c1= seq_com ( DOT -> ^( IF expr $c1) | ELSE COLON c2= seq_com DOT -> ^( IFELSE expr $c1 $c2) ) | WHILE expr COLON seq_com DOT -> ^( WHILE expr seq_com ) | FOR expr TO expr COLON seq_com DOT -> ^( FOR expr expr seq_com ) );
    public final FunParser.com_return com() throws RecognitionException {
        FunParser.com_return retval = new FunParser.com_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token ID33=null;
        Token ASSN34=null;
        Token ID36=null;
        Token LPAR37=null;
        Token RPAR39=null;
        Token IF40=null;
        Token COLON42=null;
        Token DOT43=null;
        Token ELSE44=null;
        Token COLON45=null;
        Token DOT46=null;
        Token WHILE47=null;
        Token COLON49=null;
        Token DOT51=null;
        Token FOR52=null;
        Token TO54=null;
        Token COLON56=null;
        Token DOT58=null;
        FunParser.seq_com_return c1 =null;

        FunParser.seq_com_return c2 =null;

        FunParser.expr_return expr35 =null;

        FunParser.actual_return actual38 =null;

        FunParser.expr_return expr41 =null;

        FunParser.expr_return expr48 =null;

        FunParser.seq_com_return seq_com50 =null;

        FunParser.expr_return expr53 =null;

        FunParser.expr_return expr55 =null;

        FunParser.seq_com_return seq_com57 =null;


        CommonTree ID33_tree=null;
        CommonTree ASSN34_tree=null;
        CommonTree ID36_tree=null;
        CommonTree LPAR37_tree=null;
        CommonTree RPAR39_tree=null;
        CommonTree IF40_tree=null;
        CommonTree COLON42_tree=null;
        CommonTree DOT43_tree=null;
        CommonTree ELSE44_tree=null;
        CommonTree COLON45_tree=null;
        CommonTree DOT46_tree=null;
        CommonTree WHILE47_tree=null;
        CommonTree COLON49_tree=null;
        CommonTree DOT51_tree=null;
        CommonTree FOR52_tree=null;
        CommonTree TO54_tree=null;
        CommonTree COLON56_tree=null;
        CommonTree DOT58_tree=null;
        RewriteRuleTokenStream stream_COLON=new RewriteRuleTokenStream(adaptor,"token COLON");
        RewriteRuleTokenStream stream_ASSN=new RewriteRuleTokenStream(adaptor,"token ASSN");
        RewriteRuleTokenStream stream_FOR=new RewriteRuleTokenStream(adaptor,"token FOR");
        RewriteRuleTokenStream stream_RPAR=new RewriteRuleTokenStream(adaptor,"token RPAR");
        RewriteRuleTokenStream stream_LPAR=new RewriteRuleTokenStream(adaptor,"token LPAR");
        RewriteRuleTokenStream stream_WHILE=new RewriteRuleTokenStream(adaptor,"token WHILE");
        RewriteRuleTokenStream stream_TO=new RewriteRuleTokenStream(adaptor,"token TO");
        RewriteRuleTokenStream stream_DOT=new RewriteRuleTokenStream(adaptor,"token DOT");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleTokenStream stream_ELSE=new RewriteRuleTokenStream(adaptor,"token ELSE");
        RewriteRuleSubtreeStream stream_seq_com=new RewriteRuleSubtreeStream(adaptor,"rule seq_com");
        RewriteRuleSubtreeStream stream_actual=new RewriteRuleSubtreeStream(adaptor,"rule actual");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // Fun.g:79:2: ( ID ASSN expr -> ^( ASSN ID expr ) | ID LPAR actual RPAR -> ^( PROCCALL ID actual ) | IF expr COLON c1= seq_com ( DOT -> ^( IF expr $c1) | ELSE COLON c2= seq_com DOT -> ^( IFELSE expr $c1 $c2) ) | WHILE expr COLON seq_com DOT -> ^( WHILE expr seq_com ) | FOR expr TO expr COLON seq_com DOT -> ^( FOR expr expr seq_com ) )
            int alt9=5;
            switch ( input.LA(1) ) {
            case ID:
                {
                int LA9_1 = input.LA(2);

                if ( (LA9_1==ASSN) ) {
                    alt9=1;
                }
                else if ( (LA9_1==LPAR) ) {
                    alt9=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;

                }
                }
                break;
            case IF:
                {
                alt9=3;
                }
                break;
            case WHILE:
                {
                alt9=4;
                }
                break;
            case FOR:
                {
                alt9=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }

            switch (alt9) {
                case 1 :
                    // Fun.g:79:4: ID ASSN expr
                    {
                    ID33=(Token)match(input,ID,FOLLOW_ID_in_com682);  
                    stream_ID.add(ID33);


                    ASSN34=(Token)match(input,ASSN,FOLLOW_ASSN_in_com684);  
                    stream_ASSN.add(ASSN34);


                    pushFollow(FOLLOW_expr_in_com686);
                    expr35=expr();

                    state._fsp--;

                    stream_expr.add(expr35.getTree());

                    // AST REWRITE
                    // elements: expr, ASSN, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 79:31: -> ^( ASSN ID expr )
                    {
                        // Fun.g:79:34: ^( ASSN ID expr )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_ASSN.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // Fun.g:80:4: ID LPAR actual RPAR
                    {
                    ID36=(Token)match(input,ID,FOLLOW_ID_in_com715);  
                    stream_ID.add(ID36);


                    LPAR37=(Token)match(input,LPAR,FOLLOW_LPAR_in_com717);  
                    stream_LPAR.add(LPAR37);


                    pushFollow(FOLLOW_actual_in_com719);
                    actual38=actual();

                    state._fsp--;

                    stream_actual.add(actual38.getTree());

                    RPAR39=(Token)match(input,RPAR,FOLLOW_RPAR_in_com721);  
                    stream_RPAR.add(RPAR39);


                    // AST REWRITE
                    // elements: actual, ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 80:31: -> ^( PROCCALL ID actual )
                    {
                        // Fun.g:80:34: ^( PROCCALL ID actual )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(PROCCALL, "PROCCALL")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_1, stream_actual.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // Fun.g:82:4: IF expr COLON c1= seq_com ( DOT -> ^( IF expr $c1) | ELSE COLON c2= seq_com DOT -> ^( IFELSE expr $c1 $c2) )
                    {
                    IF40=(Token)match(input,IF,FOLLOW_IF_in_com755);  
                    stream_IF.add(IF40);


                    pushFollow(FOLLOW_expr_in_com757);
                    expr41=expr();

                    state._fsp--;

                    stream_expr.add(expr41.getTree());

                    COLON42=(Token)match(input,COLON,FOLLOW_COLON_in_com759);  
                    stream_COLON.add(COLON42);


                    pushFollow(FOLLOW_seq_com_in_com763);
                    c1=seq_com();

                    state._fsp--;

                    stream_seq_com.add(c1.getTree());

                    // Fun.g:83:5: ( DOT -> ^( IF expr $c1) | ELSE COLON c2= seq_com DOT -> ^( IFELSE expr $c1 $c2) )
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==DOT) ) {
                        alt8=1;
                    }
                    else if ( (LA8_0==ELSE) ) {
                        alt8=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 8, 0, input);

                        throw nvae;

                    }
                    switch (alt8) {
                        case 1 :
                            // Fun.g:83:7: DOT
                            {
                            DOT43=(Token)match(input,DOT,FOLLOW_DOT_in_com771);  
                            stream_DOT.add(DOT43);


                            // AST REWRITE
                            // elements: IF, expr, c1
                            // token labels: 
                            // rule labels: retval, c1
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                            RewriteRuleSubtreeStream stream_c1=new RewriteRuleSubtreeStream(adaptor,"rule c1",c1!=null?c1.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 83:30: -> ^( IF expr $c1)
                            {
                                // Fun.g:83:33: ^( IF expr $c1)
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(
                                stream_IF.nextNode()
                                , root_1);

                                adaptor.addChild(root_1, stream_expr.nextTree());

                                adaptor.addChild(root_1, stream_c1.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                            }
                            break;
                        case 2 :
                            // Fun.g:84:7: ELSE COLON c2= seq_com DOT
                            {
                            ELSE44=(Token)match(input,ELSE,FOLLOW_ELSE_in_com809);  
                            stream_ELSE.add(ELSE44);


                            COLON45=(Token)match(input,COLON,FOLLOW_COLON_in_com811);  
                            stream_COLON.add(COLON45);


                            pushFollow(FOLLOW_seq_com_in_com821);
                            c2=seq_com();

                            state._fsp--;

                            stream_seq_com.add(c2.getTree());

                            DOT46=(Token)match(input,DOT,FOLLOW_DOT_in_com823);  
                            stream_DOT.add(DOT46);


                            // AST REWRITE
                            // elements: c1, expr, c2
                            // token labels: 
                            // rule labels: retval, c1, c2
                            // token list labels: 
                            // rule list labels: 
                            // wildcard labels: 
                            retval.tree = root_0;
                            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);
                            RewriteRuleSubtreeStream stream_c1=new RewriteRuleSubtreeStream(adaptor,"rule c1",c1!=null?c1.tree:null);
                            RewriteRuleSubtreeStream stream_c2=new RewriteRuleSubtreeStream(adaptor,"rule c2",c2!=null?c2.tree:null);

                            root_0 = (CommonTree)adaptor.nil();
                            // 85:30: -> ^( IFELSE expr $c1 $c2)
                            {
                                // Fun.g:85:33: ^( IFELSE expr $c1 $c2)
                                {
                                CommonTree root_1 = (CommonTree)adaptor.nil();
                                root_1 = (CommonTree)adaptor.becomeRoot(
                                (CommonTree)adaptor.create(IFELSE, "IFELSE")
                                , root_1);

                                adaptor.addChild(root_1, stream_expr.nextTree());

                                adaptor.addChild(root_1, stream_c1.nextTree());

                                adaptor.addChild(root_1, stream_c2.nextTree());

                                adaptor.addChild(root_0, root_1);
                                }

                            }


                            retval.tree = root_0;

                            }
                            break;

                    }


                    }
                    break;
                case 4 :
                    // Fun.g:88:4: WHILE expr COLON seq_com DOT
                    {
                    WHILE47=(Token)match(input,WHILE,FOLLOW_WHILE_in_com890);  
                    stream_WHILE.add(WHILE47);


                    pushFollow(FOLLOW_expr_in_com892);
                    expr48=expr();

                    state._fsp--;

                    stream_expr.add(expr48.getTree());

                    COLON49=(Token)match(input,COLON,FOLLOW_COLON_in_com894);  
                    stream_COLON.add(COLON49);


                    pushFollow(FOLLOW_seq_com_in_com900);
                    seq_com50=seq_com();

                    state._fsp--;

                    stream_seq_com.add(seq_com50.getTree());

                    DOT51=(Token)match(input,DOT,FOLLOW_DOT_in_com902);  
                    stream_DOT.add(DOT51);


                    // AST REWRITE
                    // elements: WHILE, seq_com, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 89:30: -> ^( WHILE expr seq_com )
                    {
                        // Fun.g:89:33: ^( WHILE expr seq_com )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_WHILE.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_1, stream_seq_com.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 5 :
                    // Fun.g:90:4: FOR expr TO expr COLON seq_com DOT
                    {
                    FOR52=(Token)match(input,FOR,FOLLOW_FOR_in_com930);  
                    stream_FOR.add(FOR52);


                    pushFollow(FOLLOW_expr_in_com932);
                    expr53=expr();

                    state._fsp--;

                    stream_expr.add(expr53.getTree());

                    TO54=(Token)match(input,TO,FOLLOW_TO_in_com934);  
                    stream_TO.add(TO54);


                    pushFollow(FOLLOW_expr_in_com936);
                    expr55=expr();

                    state._fsp--;

                    stream_expr.add(expr55.getTree());

                    COLON56=(Token)match(input,COLON,FOLLOW_COLON_in_com938);  
                    stream_COLON.add(COLON56);


                    pushFollow(FOLLOW_seq_com_in_com943);
                    seq_com57=seq_com();

                    state._fsp--;

                    stream_seq_com.add(seq_com57.getTree());

                    DOT58=(Token)match(input,DOT,FOLLOW_DOT_in_com945);  
                    stream_DOT.add(DOT58);


                    // AST REWRITE
                    // elements: FOR, seq_com, expr, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 91:21: -> ^( FOR expr expr seq_com )
                    {
                        // Fun.g:91:24: ^( FOR expr expr seq_com )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_FOR.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_1, stream_expr.nextTree());

                        adaptor.addChild(root_1, stream_seq_com.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "com"


    public static class seq_com_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "seq_com"
    // Fun.g:94:1: seq_com : ( com )* -> ^( SEQ ( com )* ) ;
    public final FunParser.seq_com_return seq_com() throws RecognitionException {
        FunParser.seq_com_return retval = new FunParser.seq_com_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        FunParser.com_return com59 =null;


        RewriteRuleSubtreeStream stream_com=new RewriteRuleSubtreeStream(adaptor,"rule com");
        try {
            // Fun.g:95:2: ( ( com )* -> ^( SEQ ( com )* ) )
            // Fun.g:95:4: ( com )*
            {
            // Fun.g:95:4: ( com )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==FOR||(LA10_0 >= ID && LA10_0 <= IF)||LA10_0==WHILE) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // Fun.g:95:4: com
            	    {
            	    pushFollow(FOLLOW_com_in_seq_com973);
            	    com59=com();

            	    state._fsp--;

            	    stream_com.add(com59.getTree());

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            // AST REWRITE
            // elements: com
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            // wildcard labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 95:31: -> ^( SEQ ( com )* )
            {
                // Fun.g:95:34: ^( SEQ ( com )* )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(
                (CommonTree)adaptor.create(SEQ, "SEQ")
                , root_1);

                // Fun.g:95:40: ( com )*
                while ( stream_com.hasNext() ) {
                    adaptor.addChild(root_1, stream_com.nextTree());

                }
                stream_com.reset();

                adaptor.addChild(root_0, root_1);
                }

            }


            retval.tree = root_0;

            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "seq_com"


    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "expr"
    // Fun.g:101:1: expr : sec_expr ( ( EQ ^| LT ^| GT ^| ASSN ^) sec_expr )? ;
    public final FunParser.expr_return expr() throws RecognitionException {
        FunParser.expr_return retval = new FunParser.expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token EQ61=null;
        Token LT62=null;
        Token GT63=null;
        Token ASSN64=null;
        FunParser.sec_expr_return sec_expr60 =null;

        FunParser.sec_expr_return sec_expr65 =null;


        CommonTree EQ61_tree=null;
        CommonTree LT62_tree=null;
        CommonTree GT63_tree=null;
        CommonTree ASSN64_tree=null;

        try {
            // Fun.g:102:2: ( sec_expr ( ( EQ ^| LT ^| GT ^| ASSN ^) sec_expr )? )
            // Fun.g:102:4: sec_expr ( ( EQ ^| LT ^| GT ^| ASSN ^) sec_expr )?
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_sec_expr_in_expr1019);
            sec_expr60=sec_expr();

            state._fsp--;

            adaptor.addChild(root_0, sec_expr60.getTree());

            // Fun.g:104:5: ( ( EQ ^| LT ^| GT ^| ASSN ^) sec_expr )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==ASSN||LA12_0==EQ||LA12_0==GT||LA12_0==LT) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // Fun.g:104:7: ( EQ ^| LT ^| GT ^| ASSN ^) sec_expr
                    {
                    // Fun.g:104:7: ( EQ ^| LT ^| GT ^| ASSN ^)
                    int alt11=4;
                    switch ( input.LA(1) ) {
                    case EQ:
                        {
                        alt11=1;
                        }
                        break;
                    case LT:
                        {
                        alt11=2;
                        }
                        break;
                    case GT:
                        {
                        alt11=3;
                        }
                        break;
                    case ASSN:
                        {
                        alt11=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 11, 0, input);

                        throw nvae;

                    }

                    switch (alt11) {
                        case 1 :
                            // Fun.g:104:8: EQ ^
                            {
                            EQ61=(Token)match(input,EQ,FOLLOW_EQ_in_expr1030); 
                            EQ61_tree = 
                            (CommonTree)adaptor.create(EQ61)
                            ;
                            root_0 = (CommonTree)adaptor.becomeRoot(EQ61_tree, root_0);


                            }
                            break;
                        case 2 :
                            // Fun.g:104:14: LT ^
                            {
                            LT62=(Token)match(input,LT,FOLLOW_LT_in_expr1035); 
                            LT62_tree = 
                            (CommonTree)adaptor.create(LT62)
                            ;
                            root_0 = (CommonTree)adaptor.becomeRoot(LT62_tree, root_0);


                            }
                            break;
                        case 3 :
                            // Fun.g:104:20: GT ^
                            {
                            GT63=(Token)match(input,GT,FOLLOW_GT_in_expr1040); 
                            GT63_tree = 
                            (CommonTree)adaptor.create(GT63)
                            ;
                            root_0 = (CommonTree)adaptor.becomeRoot(GT63_tree, root_0);


                            }
                            break;
                        case 4 :
                            // Fun.g:104:26: ASSN ^
                            {
                            ASSN64=(Token)match(input,ASSN,FOLLOW_ASSN_in_expr1045); 
                            ASSN64_tree = 
                            (CommonTree)adaptor.create(ASSN64)
                            ;
                            root_0 = (CommonTree)adaptor.becomeRoot(ASSN64_tree, root_0);


                            }
                            break;

                    }


                    pushFollow(FOLLOW_sec_expr_in_expr1049);
                    sec_expr65=sec_expr();

                    state._fsp--;

                    adaptor.addChild(root_0, sec_expr65.getTree());

                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "expr"


    public static class sec_expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "sec_expr"
    // Fun.g:107:1: sec_expr : pri_expr ( ( PLUS ^| MINUS ^| TIMES ^| DIV ^) pri_expr )* ;
    public final FunParser.sec_expr_return sec_expr() throws RecognitionException {
        FunParser.sec_expr_return retval = new FunParser.sec_expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token PLUS67=null;
        Token MINUS68=null;
        Token TIMES69=null;
        Token DIV70=null;
        FunParser.pri_expr_return pri_expr66 =null;

        FunParser.pri_expr_return pri_expr71 =null;


        CommonTree PLUS67_tree=null;
        CommonTree MINUS68_tree=null;
        CommonTree TIMES69_tree=null;
        CommonTree DIV70_tree=null;

        try {
            // Fun.g:108:2: ( pri_expr ( ( PLUS ^| MINUS ^| TIMES ^| DIV ^) pri_expr )* )
            // Fun.g:108:4: pri_expr ( ( PLUS ^| MINUS ^| TIMES ^| DIV ^) pri_expr )*
            {
            root_0 = (CommonTree)adaptor.nil();


            pushFollow(FOLLOW_pri_expr_in_sec_expr1063);
            pri_expr66=pri_expr();

            state._fsp--;

            adaptor.addChild(root_0, pri_expr66.getTree());

            // Fun.g:109:5: ( ( PLUS ^| MINUS ^| TIMES ^| DIV ^) pri_expr )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==DIV||LA14_0==MINUS||LA14_0==PLUS||LA14_0==TIMES) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // Fun.g:109:7: ( PLUS ^| MINUS ^| TIMES ^| DIV ^) pri_expr
            	    {
            	    // Fun.g:109:7: ( PLUS ^| MINUS ^| TIMES ^| DIV ^)
            	    int alt13=4;
            	    switch ( input.LA(1) ) {
            	    case PLUS:
            	        {
            	        alt13=1;
            	        }
            	        break;
            	    case MINUS:
            	        {
            	        alt13=2;
            	        }
            	        break;
            	    case TIMES:
            	        {
            	        alt13=3;
            	        }
            	        break;
            	    case DIV:
            	        {
            	        alt13=4;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 13, 0, input);

            	        throw nvae;

            	    }

            	    switch (alt13) {
            	        case 1 :
            	            // Fun.g:109:8: PLUS ^
            	            {
            	            PLUS67=(Token)match(input,PLUS,FOLLOW_PLUS_in_sec_expr1072); 
            	            PLUS67_tree = 
            	            (CommonTree)adaptor.create(PLUS67)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(PLUS67_tree, root_0);


            	            }
            	            break;
            	        case 2 :
            	            // Fun.g:109:16: MINUS ^
            	            {
            	            MINUS68=(Token)match(input,MINUS,FOLLOW_MINUS_in_sec_expr1077); 
            	            MINUS68_tree = 
            	            (CommonTree)adaptor.create(MINUS68)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(MINUS68_tree, root_0);


            	            }
            	            break;
            	        case 3 :
            	            // Fun.g:109:25: TIMES ^
            	            {
            	            TIMES69=(Token)match(input,TIMES,FOLLOW_TIMES_in_sec_expr1082); 
            	            TIMES69_tree = 
            	            (CommonTree)adaptor.create(TIMES69)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(TIMES69_tree, root_0);


            	            }
            	            break;
            	        case 4 :
            	            // Fun.g:109:34: DIV ^
            	            {
            	            DIV70=(Token)match(input,DIV,FOLLOW_DIV_in_sec_expr1087); 
            	            DIV70_tree = 
            	            (CommonTree)adaptor.create(DIV70)
            	            ;
            	            root_0 = (CommonTree)adaptor.becomeRoot(DIV70_tree, root_0);


            	            }
            	            break;

            	    }


            	    pushFollow(FOLLOW_pri_expr_in_sec_expr1091);
            	    pri_expr71=pri_expr();

            	    state._fsp--;

            	    adaptor.addChild(root_0, pri_expr71.getTree());

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "sec_expr"


    public static class pri_expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "pri_expr"
    // Fun.g:112:1: pri_expr : ( FALSE -> FALSE | TRUE -> TRUE | NUM -> NUM | ID -> ID | ID LPAR actual RPAR -> ^( FUNCCALL ID actual ) | NOT pri_expr -> ^( NOT pri_expr ) | LPAR expr RPAR -> expr );
    public final FunParser.pri_expr_return pri_expr() throws RecognitionException {
        FunParser.pri_expr_return retval = new FunParser.pri_expr_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        Token FALSE72=null;
        Token TRUE73=null;
        Token NUM74=null;
        Token ID75=null;
        Token ID76=null;
        Token LPAR77=null;
        Token RPAR79=null;
        Token NOT80=null;
        Token LPAR82=null;
        Token RPAR84=null;
        FunParser.actual_return actual78 =null;

        FunParser.pri_expr_return pri_expr81 =null;

        FunParser.expr_return expr83 =null;


        CommonTree FALSE72_tree=null;
        CommonTree TRUE73_tree=null;
        CommonTree NUM74_tree=null;
        CommonTree ID75_tree=null;
        CommonTree ID76_tree=null;
        CommonTree LPAR77_tree=null;
        CommonTree RPAR79_tree=null;
        CommonTree NOT80_tree=null;
        CommonTree LPAR82_tree=null;
        CommonTree RPAR84_tree=null;
        RewriteRuleTokenStream stream_RPAR=new RewriteRuleTokenStream(adaptor,"token RPAR");
        RewriteRuleTokenStream stream_NOT=new RewriteRuleTokenStream(adaptor,"token NOT");
        RewriteRuleTokenStream stream_LPAR=new RewriteRuleTokenStream(adaptor,"token LPAR");
        RewriteRuleTokenStream stream_ID=new RewriteRuleTokenStream(adaptor,"token ID");
        RewriteRuleTokenStream stream_FALSE=new RewriteRuleTokenStream(adaptor,"token FALSE");
        RewriteRuleTokenStream stream_TRUE=new RewriteRuleTokenStream(adaptor,"token TRUE");
        RewriteRuleTokenStream stream_NUM=new RewriteRuleTokenStream(adaptor,"token NUM");
        RewriteRuleSubtreeStream stream_pri_expr=new RewriteRuleSubtreeStream(adaptor,"rule pri_expr");
        RewriteRuleSubtreeStream stream_actual=new RewriteRuleSubtreeStream(adaptor,"rule actual");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // Fun.g:113:2: ( FALSE -> FALSE | TRUE -> TRUE | NUM -> NUM | ID -> ID | ID LPAR actual RPAR -> ^( FUNCCALL ID actual ) | NOT pri_expr -> ^( NOT pri_expr ) | LPAR expr RPAR -> expr )
            int alt15=7;
            switch ( input.LA(1) ) {
            case FALSE:
                {
                alt15=1;
                }
                break;
            case TRUE:
                {
                alt15=2;
                }
                break;
            case NUM:
                {
                alt15=3;
                }
                break;
            case ID:
                {
                int LA15_4 = input.LA(2);

                if ( (LA15_4==LPAR) ) {
                    alt15=5;
                }
                else if ( ((LA15_4 >= ASSN && LA15_4 <= COLON)||(LA15_4 >= DIV && LA15_4 <= ELSE)||LA15_4==EQ||LA15_4==FOR||LA15_4==FUNC||(LA15_4 >= GT && LA15_4 <= IF)||LA15_4==INT||(LA15_4 >= LT && LA15_4 <= MINUS)||(LA15_4 >= PLUS && LA15_4 <= PROC)||(LA15_4 >= RETURN && LA15_4 <= RPAR)||(LA15_4 >= TIMES && LA15_4 <= TO)||LA15_4==WHILE) ) {
                    alt15=4;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 15, 4, input);

                    throw nvae;

                }
                }
                break;
            case NOT:
                {
                alt15=6;
                }
                break;
            case LPAR:
                {
                alt15=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;

            }

            switch (alt15) {
                case 1 :
                    // Fun.g:113:4: FALSE
                    {
                    FALSE72=(Token)match(input,FALSE,FOLLOW_FALSE_in_pri_expr1105);  
                    stream_FALSE.add(FALSE72);


                    // AST REWRITE
                    // elements: FALSE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 113:31: -> FALSE
                    {
                        adaptor.addChild(root_0, 
                        stream_FALSE.nextNode()
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // Fun.g:114:4: TRUE
                    {
                    TRUE73=(Token)match(input,TRUE,FOLLOW_TRUE_in_pri_expr1135);  
                    stream_TRUE.add(TRUE73);


                    // AST REWRITE
                    // elements: TRUE
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 114:31: -> TRUE
                    {
                        adaptor.addChild(root_0, 
                        stream_TRUE.nextNode()
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 3 :
                    // Fun.g:115:4: NUM
                    {
                    NUM74=(Token)match(input,NUM,FOLLOW_NUM_in_pri_expr1166);  
                    stream_NUM.add(NUM74);


                    // AST REWRITE
                    // elements: NUM
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 115:31: -> NUM
                    {
                        adaptor.addChild(root_0, 
                        stream_NUM.nextNode()
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 4 :
                    // Fun.g:116:4: ID
                    {
                    ID75=(Token)match(input,ID,FOLLOW_ID_in_pri_expr1198);  
                    stream_ID.add(ID75);


                    // AST REWRITE
                    // elements: ID
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 116:31: -> ID
                    {
                        adaptor.addChild(root_0, 
                        stream_ID.nextNode()
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 5 :
                    // Fun.g:117:4: ID LPAR actual RPAR
                    {
                    ID76=(Token)match(input,ID,FOLLOW_ID_in_pri_expr1231);  
                    stream_ID.add(ID76);


                    LPAR77=(Token)match(input,LPAR,FOLLOW_LPAR_in_pri_expr1233);  
                    stream_LPAR.add(LPAR77);


                    pushFollow(FOLLOW_actual_in_pri_expr1235);
                    actual78=actual();

                    state._fsp--;

                    stream_actual.add(actual78.getTree());

                    RPAR79=(Token)match(input,RPAR,FOLLOW_RPAR_in_pri_expr1237);  
                    stream_RPAR.add(RPAR79);


                    // AST REWRITE
                    // elements: ID, actual
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 117:31: -> ^( FUNCCALL ID actual )
                    {
                        // Fun.g:117:34: ^( FUNCCALL ID actual )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        (CommonTree)adaptor.create(FUNCCALL, "FUNCCALL")
                        , root_1);

                        adaptor.addChild(root_1, 
                        stream_ID.nextNode()
                        );

                        adaptor.addChild(root_1, stream_actual.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 6 :
                    // Fun.g:119:4: NOT pri_expr
                    {
                    NOT80=(Token)match(input,NOT,FOLLOW_NOT_in_pri_expr1293);  
                    stream_NOT.add(NOT80);


                    pushFollow(FOLLOW_pri_expr_in_pri_expr1295);
                    pri_expr81=pri_expr();

                    state._fsp--;

                    stream_pri_expr.add(pri_expr81.getTree());

                    // AST REWRITE
                    // elements: NOT, pri_expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 119:31: -> ^( NOT pri_expr )
                    {
                        // Fun.g:119:34: ^( NOT pri_expr )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(
                        stream_NOT.nextNode()
                        , root_1);

                        adaptor.addChild(root_1, stream_pri_expr.nextTree());

                        adaptor.addChild(root_0, root_1);
                        }

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 7 :
                    // Fun.g:120:4: LPAR expr RPAR
                    {
                    LPAR82=(Token)match(input,LPAR,FOLLOW_LPAR_in_pri_expr1322);  
                    stream_LPAR.add(LPAR82);


                    pushFollow(FOLLOW_expr_in_pri_expr1324);
                    expr83=expr();

                    state._fsp--;

                    stream_expr.add(expr83.getTree());

                    RPAR84=(Token)match(input,RPAR,FOLLOW_RPAR_in_pri_expr1326);  
                    stream_RPAR.add(RPAR84);


                    // AST REWRITE
                    // elements: expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 120:31: -> expr
                    {
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "pri_expr"


    public static class actual_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };


    // $ANTLR start "actual"
    // Fun.g:123:1: actual : ( expr -> expr | -> NOACTUAL );
    public final FunParser.actual_return actual() throws RecognitionException {
        FunParser.actual_return retval = new FunParser.actual_return();
        retval.start = input.LT(1);


        CommonTree root_0 = null;

        FunParser.expr_return expr85 =null;


        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // Fun.g:124:2: ( expr -> expr | -> NOACTUAL )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==FALSE||LA16_0==ID||LA16_0==LPAR||(LA16_0 >= NOT && LA16_0 <= NUM)||LA16_0==TRUE) ) {
                alt16=1;
            }
            else if ( (LA16_0==RPAR) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // Fun.g:124:4: expr
                    {
                    pushFollow(FOLLOW_expr_in_actual1353);
                    expr85=expr();

                    state._fsp--;

                    stream_expr.add(expr85.getTree());

                    // AST REWRITE
                    // elements: expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 124:31: -> expr
                    {
                        adaptor.addChild(root_0, stream_expr.nextTree());

                    }


                    retval.tree = root_0;

                    }
                    break;
                case 2 :
                    // Fun.g:125:31: 
                    {
                    // AST REWRITE
                    // elements: 
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    // wildcard labels: 
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"rule retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 125:31: -> NOACTUAL
                    {
                        adaptor.addChild(root_0, 
                        (CommonTree)adaptor.create(NOACTUAL, "NOACTUAL")
                        );

                    }


                    retval.tree = root_0;

                    }
                    break;

            }
            retval.stop = input.LT(-1);


            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
            adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
    	retval.tree = (CommonTree)adaptor.errorNode(input, retval.start, input.LT(-1), re);

        }

        finally {
        	// do for sure before leaving
        }
        return retval;
    }
    // $ANTLR end "actual"

    // Delegated rules


 

    public static final BitSet FOLLOW_var_decl_in_program99 = new BitSet(new long[]{0x0000000200820020L});
    public static final BitSet FOLLOW_proc_decl_in_program102 = new BitSet(new long[]{0x0000000200020000L});
    public static final BitSet FOLLOW_EOF_in_program105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PROC_in_proc_decl202 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_proc_decl204 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_LPAR_in_proc_decl210 = new BitSet(new long[]{0x0000002000800020L});
    public static final BitSet FOLLOW_formal_in_proc_decl212 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RPAR_in_proc_decl214 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_COLON_in_proc_decl216 = new BitSet(new long[]{0x0000100000B08420L});
    public static final BitSet FOLLOW_var_decl_in_proc_decl222 = new BitSet(new long[]{0x0000100000B08420L});
    public static final BitSet FOLLOW_seq_com_in_proc_decl225 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_DOT_in_proc_decl227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNC_in_proc_decl319 = new BitSet(new long[]{0x0000000000800020L});
    public static final BitSet FOLLOW_type_in_proc_decl321 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_proc_decl323 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_LPAR_in_proc_decl329 = new BitSet(new long[]{0x0000002000800020L});
    public static final BitSet FOLLOW_formal_in_proc_decl331 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RPAR_in_proc_decl333 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_COLON_in_proc_decl335 = new BitSet(new long[]{0x0000101000B08020L});
    public static final BitSet FOLLOW_var_decl_in_proc_decl341 = new BitSet(new long[]{0x0000101000B08020L});
    public static final BitSet FOLLOW_seq_com_in_proc_decl344 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RETURN_in_proc_decl350 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_expr_in_proc_decl352 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_DOT_in_proc_decl354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_formal496 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_formal498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_type_in_var_decl572 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_ID_in_var_decl574 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ASSN_in_var_decl576 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_expr_in_var_decl578 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOL_in_type610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_type641 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_com682 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ASSN_in_com684 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_expr_in_com686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_com715 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_LPAR_in_com717 = new BitSet(new long[]{0x00000420C2104000L});
    public static final BitSet FOLLOW_actual_in_com719 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RPAR_in_com721 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_com755 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_expr_in_com757 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_COLON_in_com759 = new BitSet(new long[]{0x0000100000308C00L});
    public static final BitSet FOLLOW_seq_com_in_com763 = new BitSet(new long[]{0x0000000000000C00L});
    public static final BitSet FOLLOW_DOT_in_com771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ELSE_in_com809 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_COLON_in_com811 = new BitSet(new long[]{0x0000100000308400L});
    public static final BitSet FOLLOW_seq_com_in_com821 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_DOT_in_com823 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WHILE_in_com890 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_expr_in_com892 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_COLON_in_com894 = new BitSet(new long[]{0x0000100000308400L});
    public static final BitSet FOLLOW_seq_com_in_com900 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_DOT_in_com902 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_com930 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_expr_in_com932 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_TO_in_com934 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_expr_in_com936 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_COLON_in_com938 = new BitSet(new long[]{0x0000100000308400L});
    public static final BitSet FOLLOW_seq_com_in_com943 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_DOT_in_com945 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_com_in_seq_com973 = new BitSet(new long[]{0x0000100000308002L});
    public static final BitSet FOLLOW_sec_expr_in_expr1019 = new BitSet(new long[]{0x0000000004082012L});
    public static final BitSet FOLLOW_EQ_in_expr1030 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_LT_in_expr1035 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_GT_in_expr1040 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_ASSN_in_expr1045 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_sec_expr_in_expr1049 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pri_expr_in_sec_expr1063 = new BitSet(new long[]{0x0000010108000202L});
    public static final BitSet FOLLOW_PLUS_in_sec_expr1072 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_MINUS_in_sec_expr1077 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_TIMES_in_sec_expr1082 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_DIV_in_sec_expr1087 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_pri_expr_in_sec_expr1091 = new BitSet(new long[]{0x0000010108000202L});
    public static final BitSet FOLLOW_FALSE_in_pri_expr1105 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_TRUE_in_pri_expr1135 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NUM_in_pri_expr1166 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_pri_expr1198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_pri_expr1231 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_LPAR_in_pri_expr1233 = new BitSet(new long[]{0x00000420C2104000L});
    public static final BitSet FOLLOW_actual_in_pri_expr1235 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RPAR_in_pri_expr1237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NOT_in_pri_expr1293 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_pri_expr_in_pri_expr1295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAR_in_pri_expr1322 = new BitSet(new long[]{0x00000400C2104000L});
    public static final BitSet FOLLOW_expr_in_pri_expr1324 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RPAR_in_pri_expr1326 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_actual1353 = new BitSet(new long[]{0x0000000000000002L});

}