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

import com.liferay.headless.admin.user.dto.v1_0.CustomField;
import com.liferay.headless.admin.user.dto.v1_0.CustomValue;
import com.liferay.headless.admin.user.dto.v1_0.UserAccount;
import com.liferay.portal.vulcan.jaxrs.context.EntityExtensionContext;
import com.liferay.portal.vulcan.jaxrs.context.ExtensionContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.ws.rs.ext.ContextResolver;
import java.util.*;

/**
 * @author Javier de Arcos
 */
@Component(
        property = {
                "osgi.jaxrs.application.select=(osgi.jaxrs.name=Liferay.Headless.Admin.User)",
                "osgi.jaxrs.extension=true",
                "osgi.jaxrs.name=Liferay.Headless.Admin.User.Github"
        },
        service = ContextResolver.class
)
public class UserAccountExtensionContext implements ContextResolver<ExtensionContext> {
    @Override
    public ExtensionContext getContext(Class<?> type) {
        if (type.isAssignableFrom(UserAccount.class)) {
            return new EntityExtensionContext<UserAccount>() {
                @Override
                public Map<String, Object> getEntityExtendedProperties(UserAccount userAccount) {
                    return getGithubName(userAccount)
                            .map(biographyService::getBiography)
                            .map(biography -> Collections.singletonMap("biography", (Object) biography))
                            .orElse(Collections.emptyMap());
                }

                @Override
                public Set<String> getEntityFilteredPropertyKeys(UserAccount entity) {
                    return null;
                }
            };
        }

        return null;
    }

    private Optional<String> getGithubName(UserAccount entity) {
        return Arrays.stream(entity.getCustomFields())
                .filter(customField -> GITHUB_ACCOUNT_CUSTOM_FIELD_NAME.equalsIgnoreCase(customField.getName()))
                .findFirst()
                .map(CustomField::getCustomValue)
                .map(CustomValue::getData)
                .map(Object::toString);
    }

    @Reference
    private BiographyService biographyService;

    private static final String GITHUB_ACCOUNT_CUSTOM_FIELD_NAME = "Github";
}
