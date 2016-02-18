package hoge.fuga.common.token;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * Token をキャッシュするクラス.
 * @author hirano
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TransactionTokenCache {

    /** キャッシュする入れ物. */
    private Map<String, String> tokenCache = new HashMap<>();

    /**
     * キャッシュから Token を取得します.
     * @param key キー
     * @return Token 文字列
     */
    public String get(String key) {
        return tokenCache.get(key);
    }

    /**
     * キャッシュに Token を保存します.
     * @param key キー
     * @param value Token 文字列
     * @return 保存後の Token 文字列
     */
    public String put(String key, String value) {
        return tokenCache.put(key, value);
    }

    /**
     * キャッシュから Token を削除します.
     * @param key キー
     * @return 削除前の Token 文字列
     */
    public String remove(String key) {
        return tokenCache.remove(key);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return tokenCache.entrySet().stream()
                .map(x -> "[" + x.getKey() + "=" + x.getValue() + "]")
                .collect(Collectors.joining());
    }
}
