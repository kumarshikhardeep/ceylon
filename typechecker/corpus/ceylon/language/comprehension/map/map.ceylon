doc "Construct a |Map| by evaluating the block for
     each given object and collecting the resulting
     |Entry|s."
shared Map<U,V> map<X,U,V>(iterated Iterable<X> elements,
                           Entry<U,V> of(coordinated X element)) {
    OpenMap<U,V> map = HashMap<U,V>();
    for (X x in elements) {
        map.add( of(x) );
    }
}