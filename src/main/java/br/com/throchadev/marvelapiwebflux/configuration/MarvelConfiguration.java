package br.com.throchadev.marvelapiwebflux.configuration;

import br.com.throchadev.marvelapiwebflux.utils.ConstantsUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class MarvelConfiguration {

  @Value("${client.url}")
  private String url;

  @Value("${client.ts}")
  private String ts;

  @Value("${client.apikey}")
  private String apikey;

  @Value("${client.hash}")
  private String hash;

  public String getPathCredentials() {
    return "ts" + ConstantsUtil.PATH_SEPARATOR_EQUAL + this.getTs() + ConstantsUtil.PATH_SEPARATOR_AND_COMMERCIAL + "apikey" + ConstantsUtil.PATH_SEPARATOR_EQUAL +
        this.getApikey() + ConstantsUtil.PATH_SEPARATOR_AND_COMMERCIAL + "hash" + ConstantsUtil.PATH_SEPARATOR_EQUAL + this.getHash();
  }
}
