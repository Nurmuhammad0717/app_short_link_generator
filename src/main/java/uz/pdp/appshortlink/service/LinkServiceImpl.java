package uz.pdp.appshortlink.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uz.pdp.appshortlink.entity.Link;
import uz.pdp.appshortlink.payload.LinkCrudDTO;
import uz.pdp.appshortlink.repository.LinkRepository;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;

    private final String LETTERS = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
    private final Random random = new Random();



    /**
     * 2 ta maksimum raqam 5 tasi harfga generator
     */
    public Link create(LinkCrudDTO linkCrudDTO) {

        String url;

        do
            url = generateUniqueUrl();
        while (linkRepository.existsByShortUrl(url));

        Link link = new Link();
        link.setName(linkCrudDTO.getName());
        link.setDescription(linkCrudDTO.getDescription());
        link.setDestinationUrl(linkCrudDTO.getDestinationUrl());
        link.setShortUrl(url);
        linkRepository.save(link);

        return link;
    }

    private String generateUniqueUrl() {
        StringBuilder sb = new StringBuilder();
        int numberOfDigits = 0;

        for (int i = 0; i < 8; i++) {

            boolean isLetter = random.nextBoolean();

            if (numberOfDigits >= 2) {
                isLetter = true;
            }

            if (!isLetter) {
                sb.append(random.nextInt(10));
                numberOfDigits++;
            } else {
                int indexOfLetter = random.nextInt(LETTERS.length());
                sb.append(LETTERS.charAt(indexOfLetter));
            }
        }
        return sb.toString();
    }

}
