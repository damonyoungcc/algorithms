class QuickUnion {
  id = [];

  constructor(N) {
    this.id = new Array(N);
    for (let i = 0; i < N; i++) {
      this.id[i] = i;
    }
  }

  root(i) {
    while (i !== this.id[i]) {
      i = this.id[i];
    }
    return i;
  }

  connected(p, q) {
    return this.root(p) === this.root(q);
  }

  union(p, q) {
    const rootp = this.root(p);
    const rootq = this.root(q);
    this.id[rootp] = rootq;
  }
}

const qu = new QuickUnion(10);
qu.union(4, 3);
qu.union(3, 8);
qu.union(6, 5);
qu.union(9, 4);
qu.union(2, 1);
console.log(qu.connected(8, 9));
console.log(qu.connected(5, 4));
qu.union(5, 0);
qu.union(7, 2);
qu.union(6, 1);
qu.union(7, 3);
console.log(qu.id);
console.log(qu.connected(8, 9));
