# Steps to RUN the program
mvn clean install
java -jar target/challenge-request-sampling-1.0-jar-with-dependencies.jar /path/to/requests.json.log


# The ADEX data engineer challenge

## Sampling from JSON log file

### Background

We are dealing with a huge amount of requests collected for several different 
customers per day. We want to show the customers reports based on a preview of 
their collected requests, that should have a similar distribution to the full 
request set. Therefore we need to sample the requests into a manageable set. Due
to technical limitations we need the sample to be not more than 5.000 requests 
per customer.

### The task

Available to you is a class Request and an interface SamplerInterface. You have 
to write a Java program with an implementation of SamplerInterface which should 
do the following: Given a path to a JSON log file that contains all requests, it
should produce and return an unbiased sample
([without replacement](http://stattrek.com/statistics/dictionary.aspx?definition=Sampling_without_replacement))
in the form of a List<Request> that contains 5.000 (five thousand) random
requests for each distinct customerID. If a customerID has less than 5.000
requests, then all of them must be present in the sample. Note, that the sample
is supposed to maintain the characteristics of the full request set. The sampled
requests should be written to stdout in the same format as the input. Please 
take care to handle invalid input lines -- no complex error handling needed,
just skip them, but the program shouldn't fail when the JSON is invalid or a
field or value is missing. You may want to notice the user when errors have been
detected (for example write a log message or to stderr, but not to stdout).

Please note that it is a completely valid solution whether the program is
probabilistic or deterministic, meaning:

- It is a valid solution if the sample does NOT contain exactly 5.000 requests
per customerID but contains approximately around 5.000
- It is also a valid solution if the sample contains exactly 5.000 requests per 
customerID


Please find an example structure of the input JSON log file in the resources
folder, redirecting the stdout of your program into an output file should
produce a similar file.

A couple of important things to note:

- The parsed content of the JSON log file as well as the file itself DOES NOT 
fit into memory. You have to read the data line by line, where each line is a
complete JSON string representing one request. However, you may read the file 
more than once if required (hint: it's not).
- The sample itself, List<Request> DOES fit in memory easily.

Please do not change the provided classes Request and SamplerInterface. For your
implementation you are free to add classes and structure them as you feel
necessary. Also feel free to add 3rd pary libraries if needed, e.g. for JSON
or argument parsing or logging BUT NOT for the main task of sampling.

Please also let us know how much time you spent working on the challenge. Feel 
free to use search engines and online resources to find appropriate sampling
methods, and when you do so, please document your findings. Please do not copy
and paste code.

### Submitting your solution

You can submit your solution by pushing it to your personal Github/Bitbucket 
account as a public repository and sending us the link by email, so we can git 
clone it which is the preferred way. If this is not possible you can send us the
project in a zip or tar.gz archive attached to an email.

Please provide instructions how to compile and run your solution (preferably
within this README file, just add them at the bottom). For example when using
maven (we provide a pom.xml within this project), this would be fine:

(in project root)  
$ mvn clean install  
$ java -jar target/challenge-request-sampling-1.0.jar the-requests.json.log

(Note that this won't work unless a main class is specified either in the
pom.xml, a MANIFEST, or the java command -- how to run it is your decision.)

### How we assess your solution

We will compile and run your solution according to your instructions, passing
some huge test JSON log files and analyze the produced samples. We also will
test some files with erroneous or invalid lines to test the robustness. This
part will be mostly automated.

We will also look if your code is well structured, consistent, clean, and
readable. If you must write complicated or complex parts, please comment on
them. We will also look how efficient your solution is (but the main objective
is that it gets the job done). This part clearly is a bit subjective but we'll
follow best practises.

You may add comments and notes at the end of this README as you like.


Good luck and happy coding  
The ADEX data engineering team
