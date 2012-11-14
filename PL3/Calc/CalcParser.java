// $ANTLR 3.4 .\\Calc.g 2012-11-07 15:41:31

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class CalcParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASSN", "COMMENT", "DIVIDEDBY", "EOL", "ID", "LPAR", "MINUS", "NUM", "PLUS", "PUT", "RPAR", "SET", "SPACE", "TIMES"
    };

    public static final int EOF=-1;
    public static final int ASSN=4;
    public static final int COMMENT=5;
    public static final int DIVIDEDBY=6;
    public static final int EOL=7;
    public static final int ID=8;
    public static final int LPAR=9;
    public static final int MINUS=10;
    public static final int NUM=11;
    public static final int PLUS=12;
    public static final int PUT=13;
    public static final int RPAR=14;
    public static final int SET=15;
    public static final int SPACE=16;
    public static final int TIMES=17;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public CalcParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public CalcParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    public String[] getTokenNames() { return CalcParser.tokenNames; }
    public String getGrammarFileName() { return ".\\Calc.g"; }


    	private int[] store = new int[26];
    	// ... storage for variables 'a', ..., 'z'



    // $ANTLR start "prog"
    // .\\Calc.g:10:1: prog : ( com )* EOF ;
    public final void prog() throws RecognitionException {
        try {
            // .\\Calc.g:11:2: ( ( com )* EOF )
            // .\\Calc.g:11:4: ( com )* EOF
            {
            // .\\Calc.g:11:4: ( com )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==PUT||LA1_0==SET) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // .\\Calc.g:11:4: com
            	    {
            	    pushFollow(FOLLOW_com_in_prog19);
            	    com();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            match(input,EOF,FOLLOW_EOF_in_prog22); 

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
    // $ANTLR end "prog"



    // $ANTLR start "com"
    // .\\Calc.g:16:1: com : ( PUT v= expr EOL | SET ID ASSN v= expr EOL );
    public final void com() throws RecognitionException {
        Token ID1=null;
        int v =0;


        try {
            // .\\Calc.g:17:2: ( PUT v= expr EOL | SET ID ASSN v= expr EOL )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==PUT) ) {
                alt2=1;
            }
            else if ( (LA2_0==SET) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
                case 1 :
                    // .\\Calc.g:17:4: PUT v= expr EOL
                    {
                    match(input,PUT,FOLLOW_PUT_in_com35); 

                    pushFollow(FOLLOW_expr_in_com39);
                    v=expr();

                    state._fsp--;


                    match(input,EOL,FOLLOW_EOL_in_com41); 

                     System.out.println(v); 

                    }
                    break;
                case 2 :
                    // .\\Calc.g:18:4: SET ID ASSN v= expr EOL
                    {
                    match(input,SET,FOLLOW_SET_in_com54); 

                    ID1=(Token)match(input,ID,FOLLOW_ID_in_com56); 

                    match(input,ASSN,FOLLOW_ASSN_in_com58); 

                    pushFollow(FOLLOW_expr_in_com66);
                    v=expr();

                    state._fsp--;


                    match(input,EOL,FOLLOW_EOL_in_com68); 

                     int a =
                    		                         (ID1!=null?ID1.getText():null).charAt(0) - 'a'; 
                    		                       store[a] = v; 

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
    // .\\Calc.g:26:1: expr returns [int value] : v1= term ( PLUS v2= term | MINUS v2= term | TIMES v2= term | DIVIDEDBY v2= term )* ;
    public final int expr() throws RecognitionException {
        int value = 0;


        int v1 =0;

        int v2 =0;


        try {
            // .\\Calc.g:27:2: (v1= term ( PLUS v2= term | MINUS v2= term | TIMES v2= term | DIVIDEDBY v2= term )* )
            // .\\Calc.g:27:4: v1= term ( PLUS v2= term | MINUS v2= term | TIMES v2= term | DIVIDEDBY v2= term )*
            {
            pushFollow(FOLLOW_term_in_expr119);
            v1=term();

            state._fsp--;


             value = v1; 

            // .\\Calc.g:28:3: ( PLUS v2= term | MINUS v2= term | TIMES v2= term | DIVIDEDBY v2= term )*
            loop3:
            do {
                int alt3=5;
                switch ( input.LA(1) ) {
                case PLUS:
                    {
                    alt3=1;
                    }
                    break;
                case MINUS:
                    {
                    alt3=2;
                    }
                    break;
                case TIMES:
                    {
                    alt3=3;
                    }
                    break;
                case DIVIDEDBY:
                    {
                    alt3=4;
                    }
                    break;

                }

                switch (alt3) {
            	case 1 :
            	    // .\\Calc.g:28:5: PLUS v2= term
            	    {
            	    match(input,PLUS,FOLLOW_PLUS_in_expr140); 

            	    pushFollow(FOLLOW_term_in_expr144);
            	    v2=term();

            	    state._fsp--;


            	     value += v2; 

            	    }
            	    break;
            	case 2 :
            	    // .\\Calc.g:29:5: MINUS v2= term
            	    {
            	    match(input,MINUS,FOLLOW_MINUS_in_expr158); 

            	    pushFollow(FOLLOW_term_in_expr162);
            	    v2=term();

            	    state._fsp--;


            	     value -= v2; 

            	    }
            	    break;
            	case 3 :
            	    // .\\Calc.g:30:5: TIMES v2= term
            	    {
            	    match(input,TIMES,FOLLOW_TIMES_in_expr175); 

            	    pushFollow(FOLLOW_term_in_expr179);
            	    v2=term();

            	    state._fsp--;


            	     value *= v2; 

            	    }
            	    break;
            	case 4 :
            	    // .\\Calc.g:31:5: DIVIDEDBY v2= term
            	    {
            	    match(input,DIVIDEDBY,FOLLOW_DIVIDEDBY_in_expr192); 

            	    pushFollow(FOLLOW_term_in_expr196);
            	    v2=term();

            	    state._fsp--;


            	     value /=v2;	

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return value;
    }
    // $ANTLR end "expr"



    // $ANTLR start "term"
    // .\\Calc.g:35:1: term returns [int value] : ( NUM | ID | LPAR v= expr RPAR );
    public final int term() throws RecognitionException {
        int value = 0;


        Token NUM2=null;
        Token ID3=null;
        int v =0;


        try {
            // .\\Calc.g:36:2: ( NUM | ID | LPAR v= expr RPAR )
            int alt4=3;
            switch ( input.LA(1) ) {
            case NUM:
                {
                alt4=1;
                }
                break;
            case ID:
                {
                alt4=2;
                }
                break;
            case LPAR:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // .\\Calc.g:36:4: NUM
                    {
                    NUM2=(Token)match(input,NUM,FOLLOW_NUM_in_term241); 

                     value = Integer.parseInt(
                    		                         (NUM2!=null?NUM2.getText():null)); 

                    }
                    break;
                case 2 :
                    // .\\Calc.g:38:4: ID
                    {
                    ID3=(Token)match(input,ID,FOLLOW_ID_in_term265); 

                     int a =
                    		                         (ID3!=null?ID3.getText():null).charAt(0) - 'a'; 
                    		                       value = store[a]; 

                    }
                    break;
                case 3 :
                    // .\\Calc.g:41:4: LPAR v= expr RPAR
                    {
                    match(input,LPAR,FOLLOW_LPAR_in_term290); 

                    pushFollow(FOLLOW_expr_in_term294);
                    v=expr();

                    state._fsp--;


                    match(input,RPAR,FOLLOW_RPAR_in_term296); 

                     value = v; 

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
        return value;
    }
    // $ANTLR end "term"

    // Delegated rules


 

    public static final BitSet FOLLOW_com_in_prog19 = new BitSet(new long[]{0x000000000000A000L});
    public static final BitSet FOLLOW_EOF_in_prog22 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_PUT_in_com35 = new BitSet(new long[]{0x0000000000000B00L});
    public static final BitSet FOLLOW_expr_in_com39 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EOL_in_com41 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SET_in_com54 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_ID_in_com56 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_ASSN_in_com58 = new BitSet(new long[]{0x0000000000000B00L});
    public static final BitSet FOLLOW_expr_in_com66 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_EOL_in_com68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_expr119 = new BitSet(new long[]{0x0000000000021442L});
    public static final BitSet FOLLOW_PLUS_in_expr140 = new BitSet(new long[]{0x0000000000000B00L});
    public static final BitSet FOLLOW_term_in_expr144 = new BitSet(new long[]{0x0000000000021442L});
    public static final BitSet FOLLOW_MINUS_in_expr158 = new BitSet(new long[]{0x0000000000000B00L});
    public static final BitSet FOLLOW_term_in_expr162 = new BitSet(new long[]{0x0000000000021442L});
    public static final BitSet FOLLOW_TIMES_in_expr175 = new BitSet(new long[]{0x0000000000000B00L});
    public static final BitSet FOLLOW_term_in_expr179 = new BitSet(new long[]{0x0000000000021442L});
    public static final BitSet FOLLOW_DIVIDEDBY_in_expr192 = new BitSet(new long[]{0x0000000000000B00L});
    public static final BitSet FOLLOW_term_in_expr196 = new BitSet(new long[]{0x0000000000021442L});
    public static final BitSet FOLLOW_NUM_in_term241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_term265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAR_in_term290 = new BitSet(new long[]{0x0000000000000B00L});
    public static final BitSet FOLLOW_expr_in_term294 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_RPAR_in_term296 = new BitSet(new long[]{0x0000000000000002L});

}