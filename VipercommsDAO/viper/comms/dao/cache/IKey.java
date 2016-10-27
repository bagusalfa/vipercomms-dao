package viper.comms.dao.cache;
public interface IKey {

    /**
    Indicates if cache entries that apply to
    this key also apply to the specified key.
    */
    boolean generalizes(IKey otherKey);

}
