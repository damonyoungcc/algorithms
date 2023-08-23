class QuickFind {
  id = [];

  constructor(N) {
    this.id = new Array(N);
    for (let i = 0; i < N; i++) {
      this.id[i] = i;
    }
  }

  connected(p, q) {
    return this.id[p] === this.id[q];
  }

  union(p, q) {
    const pid = this.id[p];
    const qid = this.id[q];
    for (let i = 0; i < this.id.length; i++) {
      if (this.id[i] === pid) {
        this.id[i] = qid;
      }
    }
  }
}

const qf = new QuickFind(10);
qf.union(4, 3);
qf.union(3, 8);
qf.union(6, 5);
qf.union(9, 4);
qf.union(2, 1);
console.log(qf.connected(8, 9));
console.log(qf.connected(5, 0));
qf.union(5, 0);
qf.union(7, 2);
qf.union(6, 1);
console.log(qf.id);
console.log(qf.connected(8, 9));
