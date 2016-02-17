package hoge.fuga.common.token;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Node;
import org.thymeleaf.processor.element.AbstractMarkupSubstitutionElementProcessor;
import org.thymeleaf.spring4.context.SpringWebContext;

/**
 * Token を保持する input タグを生成する Processor クラス.
 * @author hirano
 */
public class TransactionTokenElementProcessor extends AbstractMarkupSubstitutionElementProcessor {

    /**
     * コンストラクタ.
     */
    public TransactionTokenElementProcessor() {
        super("token"); // タグ名を指定.
    }

    /*
     * (non-Javadoc)
     * @see org.thymeleaf.processor.element.AbstractMarkupSubstitutionElementProcessor#getMarkupSubstitutes(org.thymeleaf.Arguments, org.thymeleaf.dom.Element)
     */
    // タグの生成.
    @Override
    protected List<Node> getMarkupSubstitutes(Arguments arguments, Element element) {
        final ApplicationContext appCtx = ((SpringWebContext)arguments.getContext()).getApplicationContext();
        final TransactionTokenCache transactionTokenCache = appCtx.getBean(TransactionTokenCache.class);
        // セッションから Token を取得します.
        final String controllerName = element.getAttributeValue("controller");
        final String namespace = element.getAttributeValue("namespace");
        final String key = TransactionTokenKeyGenerator.generate(controllerName, namespace);
        final String token = transactionTokenCache.get(key);
        // input type="hidden" の element を生成します.
        final Element container = new Element("input");
        container.setAttribute("type", "hidden");
        container.setAttribute("name", "_token");
        container.setAttribute("value", token);
        // Node のリストの要素に詰めて返却します.
        final List<Node> nodes = new ArrayList<>();
        nodes.add(container);
        return nodes;
    }

    /*
     * (non-Javadoc)
     * @see org.thymeleaf.processor.AbstractProcessor#getPrecedence()
     */
    // 優先順位
    @Override
    public int getPrecedence() {
        return 1000;
    }
}
