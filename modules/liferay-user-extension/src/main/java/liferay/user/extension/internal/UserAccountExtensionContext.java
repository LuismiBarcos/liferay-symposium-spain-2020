/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package liferay.user.extension.internal;

import com.liferay.headless.admin.user.dto.v1_0.UserAccount;
import com.liferay.portal.vulcan.jaxrs.context.EntityExtensionContext;
import com.liferay.portal.vulcan.jaxrs.context.ExtensionContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.ext.ContextResolver;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Javier de Arcos
 */
@Component(
        property = {
                "osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Admin.User)",
                "osgi.jaxrs.extension=true",
                "osgi.jaxrs.name=Liferay.Headless.Admin.User.AvatarExtension"
        },
        service = ContextResolver.class
)
public class UserAccountExtensionContext implements ContextResolver<ExtensionContext> {
    @Override
    public ExtensionContext getContext(Class<?> type) {
        if (type.isAssignableFrom(UserAccount.class)) {
            return new EntityExtensionContext<UserAccount>() {
                @Override
                public Map<String, Object> getEntityExtendedProperties(UserAccount entity) {
                    return Collections.singletonMap("avatar", adorableAvatarService.getAvatar(entity.getId()));
                }

                @Override
                public Set<String> getEntityFilteredPropertyKeys(UserAccount entity) {
                    return null;
                }
            };
        }

        return null;
    }

    @Reference
    private AdorableAvatarService adorableAvatarService;
}
