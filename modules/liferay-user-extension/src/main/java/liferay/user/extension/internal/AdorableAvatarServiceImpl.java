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

import org.osgi.service.component.annotations.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Javier de Arcos
 */
@Component(
        service = AdorableAvatarService.class
)
public class AdorableAvatarServiceImpl implements AdorableAvatarService {
    @Override
    public String getAvatar(long id) {
        try {
            URL url = new URL("https://api.adorable.io/avatars/250/" + id);

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            StringBuilder sb = new StringBuilder();

            int responseCode = httpURLConnection.getResponseCode();

            InputStream inputStream = null;

            if (responseCode > 299) {
                inputStream = httpURLConnection.getErrorStream();
            }
            else {
                inputStream = httpURLConnection.getInputStream();
            }

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream));

            while (true) {
                String line = bufferedReader.readLine();

                if (line == null) {
                    break;
                }

                sb.append(line);
            }

            bufferedReader.close();

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
