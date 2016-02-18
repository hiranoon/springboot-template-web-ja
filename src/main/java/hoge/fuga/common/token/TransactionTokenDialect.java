package hoge.fuga.common.token;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.processor.IProcessor;

/**
 * Token に関する Processor を登録する Dialect クラス.
 * @author hirano
 */
public class TransactionTokenDialect extends AbstractDialect {

    /*
     * (non-Javadoc)
     * @see org.thymeleaf.dialect.IDialect#getPrefix()
     */
    @Override
    public String getPrefix() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.thymeleaf.dialect.AbstractDialect#getProcessors()
     */
    @Override
    public Set<IProcessor> getProcessors() {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new TransactionTokenElementProcessor());
        return processors;
    }
}
