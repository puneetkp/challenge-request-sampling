package com.theadex.dataengineer.challenge;

import java.util.List;

public interface SamplerInterface {
    List<Request> sampleRequests(String pathToJsonLogFile);
}
