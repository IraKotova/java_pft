package ru.stqa.pft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

@SuppressWarnings("ALL")
public class TestBase {

    public boolean isIssueOpen(int issueID) throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/"+issueID+".json"))
                .returnContent().asString();
        JsonElement parsed  = new JsonParser().parse(json);
        JsonElement issue = parsed.getAsJsonObject().get("issues");
        String status = issue.getAsJsonArray().get(0).getAsJsonObject().get("state_name").getAsString();


        if (status.equals("fixed")) {
            System.out.println("fixed");
            return true;
        } else
            System.out.println("notFixed");
        return false;
    }

    public void skipIfNotFixed(int issueID) throws IOException {

        if (isIssueOpen(issueID) == false) {
            throw new SkipException("Ignored because of Issue" + issueID);
        }
    }

    public  Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}