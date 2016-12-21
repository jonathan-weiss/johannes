package ch.johannes;

import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CollectionUtilTest {

    private static final String [] NUMBER_123_ARRAY = {"1", "2", "3"};

    @Test
    public void arrayListOf() throws Exception {
        assertThat(CollectionUtil.arrayListOf("1", "2", "3"), contains("1", "2", "3"));
        assertThat(CollectionUtil.arrayListOf("1", "2", "2", "3"), contains("1", "2", "2", "3"));
        assertThat(CollectionUtil.arrayListOf(NUMBER_123_ARRAY), contains("1", "2", "3"));
        assertThat(CollectionUtil.arrayListOf().isEmpty(), is(Boolean.TRUE));
        assertThat(CollectionUtil.arrayListOf("1"), contains("1"));
        assertThat(CollectionUtil.arrayListOf((String)null), contains(nullValue()));
    }

    @Test
    public void linkedListOf() throws Exception {
        assertThat(CollectionUtil.linkedListOf("1", "2", "3"), contains("1", "2", "3"));
        assertThat(CollectionUtil.linkedListOf("1", "2", "2", "3"), contains("1", "2", "2", "3"));
        assertThat(CollectionUtil.linkedListOf(NUMBER_123_ARRAY), contains("1", "2", "3"));
        assertThat(CollectionUtil.linkedListOf().isEmpty(), is(Boolean.TRUE));
        assertThat(CollectionUtil.linkedListOf("1"), contains("1"));
        assertThat(CollectionUtil.linkedListOf((String)null), contains(nullValue()));
    }

    @Test
    public void linkedHashSetOf() throws Exception {
        assertThat(CollectionUtil.linkedHashSetOf("1", "2", "3"), contains("1", "2", "3"));
        assertThat(CollectionUtil.linkedHashSetOf("1", "2", "2", "3"), contains("1", "2", "3"));
        assertThat(CollectionUtil.linkedHashSetOf(NUMBER_123_ARRAY), contains("1", "2", "3"));
        assertThat(CollectionUtil.linkedHashSetOf().isEmpty(), is(Boolean.TRUE));
        assertThat(CollectionUtil.linkedHashSetOf("1"), contains("1"));
        assertThat(CollectionUtil.linkedHashSetOf((String)null), contains(nullValue()));
    }

    @Test
    public void hashSetOf() throws Exception {
        assertThat(CollectionUtil.hashSetOf("1", "2", "3"), containsInAnyOrder("1", "2", "3"));
        assertThat(CollectionUtil.hashSetOf("1", "2", "2", "3"), containsInAnyOrder("1", "2", "3"));
        assertThat(CollectionUtil.hashSetOf(NUMBER_123_ARRAY), containsInAnyOrder("1", "2", "3"));
        assertThat(CollectionUtil.hashSetOf().isEmpty(), is(Boolean.TRUE));
        assertThat(CollectionUtil.hashSetOf("1"), contains("1"));
        assertThat(CollectionUtil.hashSetOf((String)null), contains(nullValue()));
    }



}