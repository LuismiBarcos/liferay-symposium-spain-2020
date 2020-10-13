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
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLTypeExtension;
import com.liferay.portal.vulcan.graphql.contributor.GraphQLContributor;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Javier de Arcos
 */
@Component(immediate = true, service = GraphQLContributor.class)
public class UserAccountGraphQLContributor implements GraphQLContributor {

    @Activate
    public void activate(BundleContext bundleContext) {
        UserAccountQueryContributor.setBiographyService(biographyService);
    }

    @Override
    public String getPath() {
        return "/headless-admin-user-graphql/v1_0";
    }

    @Override
    public UserAccountQueryContributor getQuery() {
        return new UserAccountQueryContributor();
    }

    public static class UserAccountQueryContributor {

        @GraphQLTypeExtension(UserAccount.class)
        public class UserAccountBiographyTypeExtension {

            public UserAccountBiographyTypeExtension(UserAccount userAccount) {
                this.userAccount = userAccount;
            }

            @GraphQLField
            public String biography() {
                CustomField[] customFields = userAccount.getCustomFields();

                return getGithubName(userAccount)
                        .map(biographyService::getBiography)
                        .orElse(null);
            }

            private final UserAccount userAccount;
        }

        private Optional<String> getGithubName(UserAccount entity) {
            return Arrays.stream(entity.getCustomFields())
                    .filter(customField -> GITHUB_ACCOUNT_CUSTOM_FIELD_NAME.equalsIgnoreCase(customField.getName()))
                    .findFirst()
                    .map(CustomField::getCustomValue)
                    .map(CustomValue::getData)
                    .map(Object::toString);
        }

        public static void setBiographyService(BiographyService service) {
            biographyService = service;
        }

        private static BiographyService biographyService;
    }

    @Reference
    private BiographyService biographyService;

    private static final String GITHUB_ACCOUNT_CUSTOM_FIELD_NAME = "Github";
}
