package ch.johannes;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CollectionUtilTest {

    private static final String [] NUMBER_123_ARRAY = {"1", "2", "3"};

    @Test
    public void listOf() throws Exception {
        assertThat(CollectionUtil.listOf("1", "2", "3"), contains("1", "2", "3"));
        assertThat(CollectionUtil.listOf("1", "2", "2", "3"), contains("1", "2", "2", "3"));
        assertThat(CollectionUtil.listOf(NUMBER_123_ARRAY), contains("1", "2", "3"));
        assertThat(CollectionUtil.listOf().isEmpty(), is(Boolean.TRUE));
        assertThat(CollectionUtil.listOf("1"), contains("1"));
        assertThat(CollectionUtil.listOf((String)null), contains(nullValue()));
    }

    @Test
    public void listOfWithCollection() throws Exception {
        final List<String> firstCollection = CollectionUtil.listOf("a", "b", "c");
        assertThat(CollectionUtil.listOf(firstCollection, (String)null), contains("a", "b", "c", null));
        assertThat(CollectionUtil.listOf(firstCollection, "1", "2", "c"), contains("a", "b", "c", "1", "2", "c"));
        assertThat(CollectionUtil.listOf(null, "1", "2", "c"), contains("1", "2", "c"));
        assertThat(CollectionUtil.listOf(firstCollection, "1", "2", "2", "c"), contains("a", "b", "c", "1", "2", "2", "c"));
        assertThat(CollectionUtil.listOf(firstCollection, NUMBER_123_ARRAY), contains("a", "b", "c", "1", "2", "3"));
        assertThat(CollectionUtil.listOf(firstCollection), contains("a", "b", "c"));
        assertThat(CollectionUtil.listOf(firstCollection, "1"), contains("a", "b", "c", "1"));
    }

    @Test
    public void listOfWithTwoCollection() throws Exception {
        final List<String> firstCollection = CollectionUtil.listOf("a", "b", "c");
        final List<String> secondCollection = CollectionUtil.listOf("1", "2", "c");
        assertThat(CollectionUtil.listOf(firstCollection, secondCollection), contains("a", "b", "c", "1", "2", "c"));
        assertThat(CollectionUtil.listOf(null, secondCollection), contains("1", "2", "c"));
        assertThat(CollectionUtil.listOf(firstCollection, (List<String>)null), contains("a", "b", "c"));
        assertThat(CollectionUtil.listOf(null, (List<String>)null), empty());
    }

    @Test
    public void setOf() throws Exception {
        assertThat(CollectionUtil.setOf("1", "2", "3"), contains("1", "2", "3"));
        assertThat(CollectionUtil.setOf("1", "2", "2", "3"), contains("1", "2", "3"));
        assertThat(CollectionUtil.setOf(NUMBER_123_ARRAY), contains("1", "2", "3"));
        assertThat(CollectionUtil.setOf().isEmpty(), is(Boolean.TRUE));
        assertThat(CollectionUtil.setOf("1"), contains("1"));
        assertThat(CollectionUtil.setOf((String)null), contains(nullValue()));
    }

    @Test
    public void setOfWithCollection() throws Exception {
        final List<String> firstCollection = CollectionUtil.listOf("a", "b", "c");

        assertThat(CollectionUtil.setOf(firstCollection, "1", "2", "3"), contains("a", "b", "c", "1", "2", "3"));
        assertThat(CollectionUtil.setOf(firstCollection, "1", "2", "2", "3"), contains("a", "b", "c", "1", "2", "3"));
        assertThat(CollectionUtil.setOf(firstCollection, NUMBER_123_ARRAY), contains("a", "b", "c", "1", "2", "3"));
        assertThat(CollectionUtil.setOf(firstCollection), contains("a", "b", "c"));
        assertThat(CollectionUtil.setOf(firstCollection, "1"), contains("a", "b", "c", "1"));
        assertThat(CollectionUtil.setOf(firstCollection, (String)null), contains("a", "b", "c", null));
    }

    @Test
    public void setOfWithTwoCollection() throws Exception {
        final List<String> firstCollection = CollectionUtil.listOf("a", "b", "c");
        final List<String> secondCollection = CollectionUtil.listOf("1", "2", "c");

        assertThat(CollectionUtil.setOf(firstCollection, secondCollection), contains("a", "b", "c", "1", "2"));
        assertThat(CollectionUtil.setOf(firstCollection, (List<String>)null), contains("a", "b", "c"));
        assertThat(CollectionUtil.setOf(null, secondCollection), contains("1", "2", "c"));
        assertThat(CollectionUtil.setOf(null, (List<String>)null), empty());
    }

}