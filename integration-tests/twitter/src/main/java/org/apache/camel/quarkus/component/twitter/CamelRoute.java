/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.quarkus.component.twitter;

import io.quarkus.runtime.annotations.RegisterForReflection;
import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@RegisterForReflection
public class CamelRoute extends RouteBuilder {

    @ConfigProperty(name = "twitter.user.name")
    String twitterUserName;

    @Override
    public void configure() {
        from("twitter-timeline:user?user=ApacheCamel&count=1")
            .to("log:timeline?showAll=true");

        from("twitter-search:#ApacheCamel?count=1")
            .to("log:search?showAll=true");

        fromF("twitter-directmessage://%s?count=1", twitterUserName)
            .to("log:directmessage?showAll=true");
    }
}