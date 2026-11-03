package com.zc.mybackcrud.config;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.comm.SignVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OssConfig {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;
    @Value("${aliyun.oss.region}")
    private String region;

    @Bean
    public OSS ossClient(ClientBuilderConfiguration clientConfig) throws Exception {
        CredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        return OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientConfig)
                .region(region)
                .build();
    }

    @Bean
    public ClientBuilderConfiguration clientBuilderConfiguration() {
        ClientBuilderConfiguration cfg = new ClientBuilderConfiguration();
        cfg.setSignatureVersion(SignVersion.V4);
        return cfg;
    }
}