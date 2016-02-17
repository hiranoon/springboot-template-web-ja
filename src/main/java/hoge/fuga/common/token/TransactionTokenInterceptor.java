package hoge.fuga.common.token;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Token チェックを行う Interceptor クラス.
 * @author hirano
 */
public class TransactionTokenInterceptor implements HandlerInterceptor {

    /** ロガー. */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /** Token をキャッシュするクラス. */
    @Autowired
    TransactionTokenCache transactionTokenCache;

    /*
     * (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler)
            throws Exception {
        // HandlerMethod を取得します.
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // Token を生成します.
        publichTransactionToken(handlerMethod);
        // Token のチェックをします.
        validateTransactionToken(handlerMethod, request);
        // 以降の処理を継続するため true を返却します.
        return true;
    }

    /**
     * Token を発行し、セッションに保持します.
     * @param handlerMethod Controller の {@link HandlerMethod}
     */
    private void publichTransactionToken(HandlerMethod handlerMethod) {
        // アノテーションを取得します.
        TransactionToken annotation = handlerMethod.getMethodAnnotation(TransactionToken.class);
        // Token 生成対象以外のメソッドは処理を終了します.
        if (annotation == null || annotation.type() != TransactionTokenType.PUBLISH) {
            return;
        }

        // Token を発行し、キャッシュします.
        String key = TransactionTokenKeyGenerator.generate(handlerMethod);
        String token = UUID.randomUUID().toString();
        transactionTokenCache.put(key, token);
        if (logger.isDebugEnabled()) {
            logger.debug("Token を発行 (key:{} token:{})", key, token);
        }
    }

    /**
     * セッションに保持した Token と画面から送信された Token が同値であるか検証します.
     * @param handlerMethod Controller の {@link HandlerMethod}
     * @param request HTTPリクエスト
     */
    private void validateTransactionToken(HandlerMethod handlerMethod, HttpServletRequest request) {
        // アノテーションを取得します.
        TransactionToken annotation = handlerMethod.getMethodAnnotation(TransactionToken.class);
        // Token 検証対象以外のメソッドは処理を終了します.
        if (annotation == null || annotation.type() != TransactionTokenType.VALIDATE) {
            return;
        }

        // セッションにキャッシュされている Token を取得します.
        String key = TransactionTokenKeyGenerator.generate(handlerMethod);
        String sessionToken = transactionTokenCache.get(key);
        if (logger.isDebugEnabled()) {
            logger.debug("Session から Token を取得 (key:{} token:{})", key, sessionToken);
        }
        // 画面から送信された Token を取得します.
        String displayToken = request.getParameter("_token");
        if (logger.isDebugEnabled()) {
            logger.debug("画面 から Token を取得 (key:{} token:{})", key, displayToken);
        }
        // 同値であるか検証します.
        if (sessionToken == null || displayToken == null || !sessionToken.equals(displayToken)) {
            logger.error("Tokenが一致しません. 画面のToken:{} セッションのToken:{}", displayToken, sessionToken);

// TODO Exceptionを投げる！

        }
        // セッションにキャッシュされている Token を削除します.
        if (sessionToken != null) {
            transactionTokenCache.remove(key);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {
        // 実装はありません.
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex)
            throws Exception {
        // 実装はありません.
    }
}
