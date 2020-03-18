package domain;

// Using seperate class as of now, because in future, maybe we can add some other features to these words as well, like weightage to a word etc.
public class Word {
    private String word;
    public Word(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    public int compareTo(Word key) {
        return this.word.compareTo(key.getWord());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Word other = (Word) obj;
        if (!word.equals(other.getWord()))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return word;
    }
}
