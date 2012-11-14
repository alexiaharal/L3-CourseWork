// $ANTLR 3.4 .\\Calc.g 2012-11-07 15:41:32

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class CalcLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public CalcLexer() {} 
    public CalcLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CalcLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return ".\\Calc.g"; }

    // $ANTLR start "PUT"
    public final void mPUT() throws RecognitionException {
        try {
            int _type = PUT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:46:5: ( 'put' )
            // .\\Calc.g:46:7: 'put'
            {
            match("put"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PUT"

    // $ANTLR start "SET"
    public final void mSET() throws RecognitionException {
        try {
            int _type = SET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:47:5: ( 'set' )
            // .\\Calc.g:47:7: 'set'
            {
            match("set"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SET"

    // $ANTLR start "ASSN"
    public final void mASSN() throws RecognitionException {
        try {
            int _type = ASSN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:49:6: ( '=' )
            // .\\Calc.g:49:8: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ASSN"

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:50:6: ( '+' )
            // .\\Calc.g:50:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:51:7: ( '-' )
            // .\\Calc.g:51:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "TIMES"
    public final void mTIMES() throws RecognitionException {
        try {
            int _type = TIMES;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:52:7: ( '*' )
            // .\\Calc.g:52:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "TIMES"

    // $ANTLR start "DIVIDEDBY"
    public final void mDIVIDEDBY() throws RecognitionException {
        try {
            int _type = DIVIDEDBY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:53:10: ( '/' )
            // .\\Calc.g:53:12: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIVIDEDBY"

    // $ANTLR start "LPAR"
    public final void mLPAR() throws RecognitionException {
        try {
            int _type = LPAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:54:6: ( '(' )
            // .\\Calc.g:54:8: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LPAR"

    // $ANTLR start "RPAR"
    public final void mRPAR() throws RecognitionException {
        try {
            int _type = RPAR;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:55:6: ( ')' )
            // .\\Calc.g:55:8: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "RPAR"

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:57:4: ( 'a' .. 'z' )
            // .\\Calc.g:
            {
            if ( (input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "NUM"
    public final void mNUM() throws RecognitionException {
        try {
            int _type = NUM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:58:5: ( ( '0' .. '9' )+ )
            // .\\Calc.g:58:7: ( '0' .. '9' )+
            {
            // .\\Calc.g:58:7: ( '0' .. '9' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0 >= '0' && LA1_0 <= '9')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // .\\Calc.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NUM"

    // $ANTLR start "EOL"
    public final void mEOL() throws RecognitionException {
        try {
            int _type = EOL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:60:5: ( ( '\\r' )? '\\n' )
            // .\\Calc.g:60:7: ( '\\r' )? '\\n'
            {
            // .\\Calc.g:60:7: ( '\\r' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='\r') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // .\\Calc.g:60:7: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }


            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EOL"

    // $ANTLR start "SPACE"
    public final void mSPACE() throws RecognitionException {
        try {
            int _type = SPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:62:7: ( ( ' ' | '\\t' )+ )
            // .\\Calc.g:62:9: ( ' ' | '\\t' )+
            {
            // .\\Calc.g:62:9: ( ' ' | '\\t' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='\t'||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // .\\Calc.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SPACE"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // .\\Calc.g:63:9: ( '/*' ( 'A' .. 'Z' | '0' .. '9' | 'a' .. 'z' | SPACE )+ '*/' EOL )
            // .\\Calc.g:63:11: '/*' ( 'A' .. 'Z' | '0' .. '9' | 'a' .. 'z' | SPACE )+ '*/' EOL
            {
            match("/*"); 



            // .\\Calc.g:63:15: ( 'A' .. 'Z' | '0' .. '9' | 'a' .. 'z' | SPACE )+
            int cnt4=0;
            loop4:
            do {
                int alt4=5;
                switch ( input.LA(1) ) {
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                case 'E':
                case 'F':
                case 'G':
                case 'H':
                case 'I':
                case 'J':
                case 'K':
                case 'L':
                case 'M':
                case 'N':
                case 'O':
                case 'P':
                case 'Q':
                case 'R':
                case 'S':
                case 'T':
                case 'U':
                case 'V':
                case 'W':
                case 'X':
                case 'Y':
                case 'Z':
                    {
                    alt4=1;
                    }
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    {
                    alt4=2;
                    }
                    break;
                case 'a':
                case 'b':
                case 'c':
                case 'd':
                case 'e':
                case 'f':
                case 'g':
                case 'h':
                case 'i':
                case 'j':
                case 'k':
                case 'l':
                case 'm':
                case 'n':
                case 'o':
                case 'p':
                case 'q':
                case 'r':
                case 's':
                case 't':
                case 'u':
                case 'v':
                case 'w':
                case 'x':
                case 'y':
                case 'z':
                    {
                    alt4=3;
                    }
                    break;
                case '\t':
                case ' ':
                    {
                    alt4=4;
                    }
                    break;

                }

                switch (alt4) {
            	case 1 :
            	    // .\\Calc.g:63:16: 'A' .. 'Z'
            	    {
            	    matchRange('A','Z'); 

            	    }
            	    break;
            	case 2 :
            	    // .\\Calc.g:63:25: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;
            	case 3 :
            	    // .\\Calc.g:63:34: 'a' .. 'z'
            	    {
            	    matchRange('a','z'); 

            	    }
            	    break;
            	case 4 :
            	    // .\\Calc.g:63:43: SPACE
            	    {
            	    mSPACE(); 


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            match("*/"); 



            mEOL(); 


             skip(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    public void mTokens() throws RecognitionException {
        // .\\Calc.g:1:8: ( PUT | SET | ASSN | PLUS | MINUS | TIMES | DIVIDEDBY | LPAR | RPAR | ID | NUM | EOL | SPACE | COMMENT )
        int alt5=14;
        switch ( input.LA(1) ) {
        case 'p':
            {
            int LA5_1 = input.LA(2);

            if ( (LA5_1=='u') ) {
                alt5=1;
            }
            else {
                alt5=10;
            }
            }
            break;
        case 's':
            {
            int LA5_2 = input.LA(2);

            if ( (LA5_2=='e') ) {
                alt5=2;
            }
            else {
                alt5=10;
            }
            }
            break;
        case '=':
            {
            alt5=3;
            }
            break;
        case '+':
            {
            alt5=4;
            }
            break;
        case '-':
            {
            alt5=5;
            }
            break;
        case '*':
            {
            alt5=6;
            }
            break;
        case '/':
            {
            int LA5_7 = input.LA(2);

            if ( (LA5_7=='*') ) {
                alt5=14;
            }
            else {
                alt5=7;
            }
            }
            break;
        case '(':
            {
            alt5=8;
            }
            break;
        case ')':
            {
            alt5=9;
            }
            break;
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'f':
        case 'g':
        case 'h':
        case 'i':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'o':
        case 'q':
        case 'r':
        case 't':
        case 'u':
        case 'v':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt5=10;
            }
            break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            {
            alt5=11;
            }
            break;
        case '\n':
        case '\r':
            {
            alt5=12;
            }
            break;
        case '\t':
        case ' ':
            {
            alt5=13;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("", 5, 0, input);

            throw nvae;

        }

        switch (alt5) {
            case 1 :
                // .\\Calc.g:1:10: PUT
                {
                mPUT(); 


                }
                break;
            case 2 :
                // .\\Calc.g:1:14: SET
                {
                mSET(); 


                }
                break;
            case 3 :
                // .\\Calc.g:1:18: ASSN
                {
                mASSN(); 


                }
                break;
            case 4 :
                // .\\Calc.g:1:23: PLUS
                {
                mPLUS(); 


                }
                break;
            case 5 :
                // .\\Calc.g:1:28: MINUS
                {
                mMINUS(); 


                }
                break;
            case 6 :
                // .\\Calc.g:1:34: TIMES
                {
                mTIMES(); 


                }
                break;
            case 7 :
                // .\\Calc.g:1:40: DIVIDEDBY
                {
                mDIVIDEDBY(); 


                }
                break;
            case 8 :
                // .\\Calc.g:1:50: LPAR
                {
                mLPAR(); 


                }
                break;
            case 9 :
                // .\\Calc.g:1:55: RPAR
                {
                mRPAR(); 


                }
                break;
            case 10 :
                // .\\Calc.g:1:60: ID
                {
                mID(); 


                }
                break;
            case 11 :
                // .\\Calc.g:1:63: NUM
                {
                mNUM(); 


                }
                break;
            case 12 :
                // .\\Calc.g:1:67: EOL
                {
                mEOL(); 


                }
                break;
            case 13 :
                // .\\Calc.g:1:71: SPACE
                {
                mSPACE(); 


                }
                break;
            case 14 :
                // .\\Calc.g:1:77: COMMENT
                {
                mCOMMENT(); 


                }
                break;

        }

    }


 

}