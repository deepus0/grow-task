package au.com.deep.grow.config;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class PropertiesSecurityConfig {
    /**
     * @INFO The environment variable NAME for Encryption Private Key
     */
    private static final String ENV_ENCRYPTION_PRIVATE_KEY = "ENV_PSW_PRIVATE_KEY";
    /**
     * @INFO Default Encryption Algorithm, DO NOT CHANGE IT
     */
    private static final String ENCRYPTION_ALGORITHM = "PBEWithMD5AndDES";
    /**
     * @INFO Default JCE Provider
     */
    private static final String ENCRYPTION_PROVIDER_NAME = "SunJCE";
    /**
     * @INFO Default Random Salt Generator
     */
    private static final String ENCRYPTION_SALT_GENERATOR = "org.jasypt.salt.RandomSaltGenerator";
    /**
     * @INFO Random IV generator for encryption (none needed for now)
     */
    private static final String ENCRYPTION_IV_GENERATOR = "org.jasypt.iv.NoIvGenerator";
    /**
     * @INFO Default string output type to retrieve name of environment variable
     */
    private static final String ENCRYPTION_STRING_OUTPUT_TYPE = "base64";

    @PostConstruct()
    public void setUp() {
        log.info("Setting up properties configuration");
    }

    @Bean(name="encryptorBean")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setAlgorithm(ENCRYPTION_ALGORITHM);
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(System.getenv(ENV_ENCRYPTION_PRIVATE_KEY));
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName(ENCRYPTION_PROVIDER_NAME);
        config.setSaltGeneratorClassName(ENCRYPTION_SALT_GENERATOR);
        config.setIvGeneratorClassName(ENCRYPTION_IV_GENERATOR);
        config.setStringOutputType(ENCRYPTION_STRING_OUTPUT_TYPE);
        encryptor.setConfig(config);
        return encryptor;
    }
}
