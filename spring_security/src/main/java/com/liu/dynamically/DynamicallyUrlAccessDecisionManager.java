package com.liu.dynamically;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.vote.AbstractAccessDecisionManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;

/**
 * 创建自定义的url认证类,这里主要逻辑就是 switch (result)部分来投票是通过验证，可以参考AbstractAccessDecisionManager的实现类进行编写
 * 参考:{@link org.springframework.security.access.vote.AffirmativeBased}
 *
 * @version 0.1
 * @auth admin.
 * @time 2018/4/2 16:42
 * @since 0.1
 */
public class DynamicallyUrlAccessDecisionManager extends AbstractAccessDecisionManager {

    public DynamicallyUrlAccessDecisionManager(List<AccessDecisionVoter<?>> decisionVoters) {
        super(decisionVoters);
    }

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        int deny = 0;

        for (AccessDecisionVoter voter : getDecisionVoters()) {
            int result = voter.vote(authentication, object, configAttributes);

            if (logger.isDebugEnabled()) {
                logger.debug("Voter: " + voter + ", returned: " + result);
            }

            switch (result) {
                //通过验证
                case AccessDecisionVoter.ACCESS_GRANTED:
                    return;
                //验证失败
                case AccessDecisionVoter.ACCESS_DENIED:
                    deny++;

                    break;

                default:
                    break;
            }
        }

        if (deny > 0) {
            throw new AccessDeniedException(messages.getMessage(
                    "AbstractAccessDecisionManager.accessDenied", "Access is denied"));
        }

        // To get this far, every AccessDecisionVoter abstained
        checkAllowIfAllAbstainDecisions();
    }
}
